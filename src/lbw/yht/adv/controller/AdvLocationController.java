package lbw.yht.adv.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.domain.AdvLocation;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.param.AdvLocationParam;
import lbw.yht.adv.domain.param.AdvTypeParam;
import lbw.yht.adv.service.AdvLocationService;
import lbw.yht.adv.service.AdvLocationService;
import lbw.yht.adv.service.AdvTypeService;
import lbw.yht.util.Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("advLocation")
public class AdvLocationController extends BaseController {

	@Autowired
	private AdvLocationService advLocationService;// 广告位
	@Autowired
	private AdvTypeService advTypeService;// 类型
	
	/**
	 * 初始化广告位
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_adv_Location")
	public ModelAndView init_adv_Location(HttpServletRequest req,
			HttpServletResponse resp) {
		
		return new ModelAndView("adv/init_adv_Location");
	}

	/**
	 * 条件查询广告位信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query_advLocation_list")
	public ModelAndView query_advLocation_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvLocationParam advLocationParam = new AdvLocationParam(
					this.initPage(request));// 分页条件
			
			String typeCode = request.getParameter("typeCode");//广告类型名称
			
			if (!Utils.Str.isEmpty(typeCode)) {
				advLocationParam.setTypeName(typeCode);
			}
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", advLocationService.queryCount(advLocationParam));
			model.put("rows", advLocationService.queryAllpageList(advLocationParam));
			this.htmlWrite(response, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 初始化增加广告类型查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_add_advLocation")
	public ModelAndView init_add_advLocation(HttpServletRequest req,
			HttpServletResponse resp) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<AdvType> advTypeList = advTypeService.queryAdvTypeAll();
		
		model.put("advTypeList", advTypeList);
		return new ModelAndView("adv/init_add_advLocation", model);
	}
	
	
	/**
	 * 增加广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("add_advtype")
	public ModelAndView add_advtype(HttpServletRequest request,
			HttpServletResponse response) {
		
		final String typeCode = request.getParameter("typeCode");// 广告类型编码
		final String advPosition = request.getParameter("advPosition");// 广告位置
		final String advFormat = request.getParameter("advFormat");// 视频:mp4 ,avi;非视频:图片格式(gif，jpg，png)
		final String advSize = request.getParameter("advSize");// 非视频:尺寸(长宽);视频:分辨率
		final String advFilesize = request.getParameter("advFilesize");// 文件大小
		final String advNo = request.getParameter("advNo");// 编号(区域)，非视频使用
		final String advDate = request.getParameter("advDate");// 创建时间(yyyy-mm-dd)
		final String advState = request.getParameter("advState");// 创建时间(yyyy-mm-dd)
		final String advContent = request.getParameter("advContent");// 竞买详情(图片，描述)
		final String advNotice = request.getParameter("advNotice");// 竞买须知
		final String advAffiche = request.getParameter("advAffiche");// 竞买公告

		// 验证器
		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(advPosition)) {
					return new Message(10, "请输入广告位置!", "advPosition");
				} else if (advPosition.length() > 100) {
					return new Message(10, "广告位置最大长度为100!", "advPosition");
				}
				
				if (Utils.Str.isEmpty(advFormat)) {
					return new Message(10, "请输入广告位格式!", "advFormat");
				} else if (advFormat.length() > 100) {
					return new Message(10, "广告位格式最大长度为50!", "advFormat");
				}
				
				if (Utils.Str.isEmpty(advSize)) {
					return new Message(10, "请输入非视频:尺寸(长宽);视频:分辨率!", "advSize");
				} else if (advSize.length() > 100) {
					return new Message(10, "非视频:尺寸(长宽);视频:分辨率最大长度为50!", "advSize");
				}
				
				if (Utils.Str.isEmpty(advFilesize)) {
					return new Message(10, "请输入文件大小!", "advFilesize");
				} else if (advFilesize.length() > 100) {
					return new Message(10, "文件大小最大长度为50!", "advSize");
				}
				
				if (Utils.Str.isEmpty(advNo)) {
					return new Message(10, "请输入编号!", "advNo");
				} else if (advNo.length() > 100) {
					return new Message(10, "编号最大长度为50!", "advNo");
				}
				
				if (Utils.Str.isEmpty(advContent)) {
					return new Message(10, "请输入竞买详情(图片，描述)!", "advContent");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvLocation advLocation = new AdvLocation();
			
				advLocation.setAdvPosition(advPosition);
				advLocation.setAdvFilesize(advFilesize);
				advLocation.setAdvFormat(advFormat);
				advLocation.setAdvSize(advSize);
				advLocation.setAdvNo(advNotice);
				advLocation.setAdvDate(new Date());
				advLocation.setAdvState(advState);
				advLocation.setAdvContent(advContent);
				advLocation.setAdvNotice(advNotice);
				advLocation.setAdvAffiche(advAffiche);
				
				advLocationService.save(advLocation);
				message = new Message(1, "新增成功!");
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "新增失败!");
			}
		}
		this.htmlWrite(response, message);
		return null;
	}
	
	/**
	 * 修改显示
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_modify_advLocation")
	public ModelAndView init_modify_advLocation(HttpServletRequest request,
			HttpServletResponse resp) {
		
		String advId = request.getParameter("advId");// 广告类型主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvLocation advLocation = advLocationService.findOneByadvId(advId);
		model.put("advLocation", advLocation);
		return new ModelAndView("adv/init_modify_advLocation", model);
	}

	/**
	 * 修改广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("modify_advLocation")
	public ModelAndView modify_advLocation(HttpServletRequest request,
			HttpServletResponse response) {
		final String advId = request.getParameter("advId");// 属性id
		final String typeCode = request.getParameter("typeCode");// 广告类型编码
		final String advPosition = request.getParameter("advPosition");// 广告位置
		final String advFormat = request.getParameter("advFormat");// 视频:mp4 ,avi;非视频:图片格式(gif，jpg，png)
		final String advSize = request.getParameter("advSize");// 非视频:尺寸(长宽);视频:分辨率
		final String advFilesize = request.getParameter("advFilesize");// 文件大小
		final String advNo = request.getParameter("advNo");// 编号(区域)，非视频使用
		final String advState = request.getParameter("advState");// 创建时间(yyyy-mm-dd)
		final String advContent = request.getParameter("advContent");// 竞买详情(图片，描述)
		final String advNotice = request.getParameter("advNotice");// 竞买须知
		final String advAffiche = request.getParameter("advAffiche");// 竞买公告

		// 验证器
		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(advPosition)) {
					return new Message(10, "请输入广告位置!", "advPosition");
				} else if (advPosition.length() > 100) {
					return new Message(10, "广告位置最大长度为100!", "advPosition");
				}
				
				if (Utils.Str.isEmpty(advFormat)) {
					return new Message(10, "请输入广告位格式!", "advFormat");
				} else if (advFormat.length() > 100) {
					return new Message(10, "广告位格式最大长度为50!", "advFormat");
				}
				
				if (Utils.Str.isEmpty(advSize)) {
					return new Message(10, "请输入非视频:尺寸(长宽);视频:分辨率!", "advSize");
				} else if (advSize.length() > 100) {
					return new Message(10, "非视频:尺寸(长宽);视频:分辨率最大长度为50!", "advSize");
				}
				
				if (Utils.Str.isEmpty(advFilesize)) {
					return new Message(10, "请输入文件大小!", "advFilesize");
				} else if (advFilesize.length() > 100) {
					return new Message(10, "文件大小最大长度为50!", "advSize");
				}
				
				if (Utils.Str.isEmpty(advNo)) {
					return new Message(10, "请输入编号!", "advNo");
				} else if (advNo.length() > 100) {
					return new Message(10, "编号最大长度为50!", "advNo");
				}
				
				if (Utils.Str.isEmpty(advContent)) {
					return new Message(10, "请输入竞买详情(图片，描述)!", "advContent");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvLocation advLocation = new AdvLocation();
				
				advLocation.setAdvId(Integer.valueOf(advId));
				advLocation.setAdvPosition(advPosition);
				advLocation.setAdvFilesize(advFilesize);
				advLocation.setAdvFormat(advFormat);
				advLocation.setAdvSize(advSize);
				advLocation.setAdvNo(advNotice);
				advLocation.setAdvDate(new Date());
				advLocation.setAdvState(advState);
				advLocation.setAdvContent(advContent);
				advLocation.setAdvNotice(advNotice);
				advLocation.setAdvAffiche(advAffiche);
				
				advLocationService.modify(advLocation);
				message = new Message(1, "修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "修改失败!");
			}
		}
		this.htmlWrite(response, message);
		return null;
	}
	
	/**
	 * 修改广告类型状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("update_adv_status")
	public ModelAndView update_adv_status(HttpServletRequest request,
			HttpServletResponse response) {
		final String advId = request.getParameter("advId");// 广告位id
		final String advState = request.getParameter("advState");// 广告位id
		Message message = null;
		try {
			AdvLocation advLocation = new AdvLocation();
			
			advLocation.setAdvId(Integer.valueOf(advId));
			advLocation.setAdvState(advState);
			
			advLocationService.updateAdvStatus(advLocation);
			
			message = new Message(1, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "修改失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}
	
	/**
	 * 删除广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("remove_advLocation")
	public ModelAndView remove_advLocation(HttpServletRequest request,
			HttpServletResponse response) {
		final String advIds = request.getParameter("advIds");// 广告位ids
		Message message = null;
		try {
			advLocationService.delete(advIds);
			message = new Message(1, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "删除失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}
	
	/**
	 * 上传帮助图片
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("upload_adv_locationImage")
	public void upload_adv_locationImage(HttpServletRequest req,
			HttpServletResponse resp) {
		ServletContext context = req.getSession().getServletContext();
		try {
			resp.setContentType("text/html");
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {

		}
		long fileMaxSize = 20971520;
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		String typeName = req.getParameter("dir");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", 0);
		String relativeUrl = null;

		if (ServletFileUpload.isMultipartContent(req)) {
			FileItem fileItem = null;

			DiskFileItemFactory factory = new DiskFileItemFactory();
			File tmpDir = new File(context.getRealPath("/") + "upload"
					+ File.separatorChar + "tmp");
			if (!tmpDir.exists()) {
				tmpDir.mkdirs();
			}
			factory.setRepository(tmpDir);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			upload.setFileSizeMax(fileMaxSize);

			try {
				List<FileItem> fileItems = upload.parseRequest(req);
				if (fileItems != null && !fileItems.isEmpty()) {
					// 遍历文件域
					for (FileItem item : fileItems) {
						if (!item.isFormField()) {
							fileItem = item;
						}
					}

					String suffix = fileItem.getName()
							.substring(fileItem.getName().lastIndexOf(".") + 1).toLowerCase();
					if (Utils.Str.isEmpty(suffix)
							|| (!Arrays.<String> asList(extMap.get(typeName).split(","))
									.contains(suffix))) {
						model.put("error", 1);
						model.put("message", "只能上传类型为" + extMap.get(typeName) + "的文件");
					} else {
						// 文件名
						String fileName = UUID.randomUUID().toString() + "." + suffix;

						// 通过年月日创建子文件夹
						String url = context.getRealPath("/") + "upload"
								+ File.separatorChar + "adv_img" + File.separatorChar
								+ typeName + File.separatorChar
								+ Utils.DT.formatDate(new Date());
						// 建立文件夹
						File dir = new File(url);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						// 创建文件
						try {
							File file = new File(url, fileName);
							fileItem.write(file);
							/*
							 * String key = "upload"+ File.separatorChar + "news_img" +
							 * File.separatorChar + fileName; OssObjectUtil ossObjectUtil =
							 * new OssObjectUtil(context);
							 */
							// 调用oss上传到阿里云服务器
							/*
							 * try { ossObjectUtil.putObject(key, file); } catch
							 * (FileNotFoundException e1) { e1.printStackTrace(); }
							 */
							relativeUrl = url + File.separatorChar + fileName;

							relativeUrl = "../upload/adv_img/" + typeName + "/"
									+ Utils.DT.formatDate(new Date()) + "/" + fileName;

						} catch (Exception e) {
							model.put("error", 1);
							model.put("message", "系统发生错误");
							LOG.error(e.getMessage());
						}

					}

				}
			} catch (FileUploadException e) {
				model.put("error", 1);
				model.put("message", "只能上传大小不超过" + fileMaxSize + "字节的文件");
				LOG.error(e.getMessage());
			}

			/*
			 * // 删除临时文件 if (fileItem != null) { fileItem.delete(); }
			 */

			model.put("url", relativeUrl);
		}

		Utils.IO.writeText(Utils.Json.toJson(model), resp);
	}

	
	
}
