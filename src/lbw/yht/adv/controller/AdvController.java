package lbw.yht.adv.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.Region;
import lbw.yht.adv.domain.param.AdvAttributeParam;
import lbw.yht.adv.service.AdvAttributeService;
import lbw.yht.adv.service.AdvTypeService;
import lbw.yht.adv.service.RegionService;
import lbw.yht.util.Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("adv")
public class AdvController extends BaseController {

	@Autowired
	private RegionService regionService;// 地区
	@Autowired
	private AdvTypeService advTypeService;// 类型
	@Autowired
	private AdvAttributeService advAttributeService;// 广告位

	/**
	 * 初始化广告位查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_adv")
	public ModelAndView initAdv(HttpServletRequest req, HttpServletResponse resp) {
		List<Region> regionList = regionService.getParentRegionAll();
		Map<String, Object> model = new HashMap<String, Object>();
		List<AdvType> advTypeList = advTypeService.queryAdvTypeAll();
		model.put("advTypeList", advTypeList);
		model.put("regionList", regionList);
		return new ModelAndView("adv/init_adv", model);
	}

	/**
	 * 通过父code查找
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("select_city")
	public ModelAndView selectCity(HttpServletRequest req,
			HttpServletResponse resp) {
		String code = req.getParameter("code");
		List<Region> regionList = regionService.getRegionByParentCode(code);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("code", 1);
		model.put("cityList", regionList);
		this.htmlWrite(resp, model);
		return null;
	}

	/**
	 * 通过子code查找
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("select_parent_city")
	public ModelAndView select_parent_city(HttpServletRequest req,
			HttpServletResponse resp) {
		String code = req.getParameter("code");
		String parentCode = regionService.getRegionByChildrenCode(code);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("code", 1);
		model.put("parentCode", parentCode);
		this.htmlWrite(resp, model);
		return null;
	}

	/**
	 * 条件查询广告位信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_adv_list")
	public ModelAndView query_adv_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvAttributeParam advAttributeParam = new AdvAttributeParam(
					this.initPage(request));// 分页条件
			String typeCode = request.getParameter("typeCode");// 类型编码
			String code = request.getParameter("code");// 地区编码
			String advStatus = request.getParameter("advStatus");// 地区编码
			if (!Utils.Str.isEmpty(typeCode)) {
				advAttributeParam.setTypeCode(typeCode);// 设置类型编码
			}
			if (!Utils.Str.isEmpty(code)) {
				advAttributeParam.setCode(code);// 设置地区编码
			}
			if (!Utils.Str.isEmpty(advStatus)) {
				advAttributeParam.setAdvStatus(advStatus);// 设置状态
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", advAttributeService.queryCount(advAttributeParam));
			model
					.put("rows", advAttributeService.queryAllpageList(advAttributeParam));
			this.htmlWrite(response, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化增加广告位查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_add_adv")
	public ModelAndView initAddAdv(HttpServletRequest req,
			HttpServletResponse resp) {
		List<Region> regionList = regionService.getParentRegionAll();
		Map<String, Object> model = new HashMap<String, Object>();
		List<AdvType> advTypeList = advTypeService.queryAdvTypeAll();
		model.put("advTypeList", advTypeList);
		model.put("regionList", regionList);
		return new ModelAndView("adv/init_add_adv", model);
	}

	/**
	 * 上传广告位图片
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("upload_adv_img")
	public ModelAndView upload_adv_img(HttpServletRequest req,
			HttpServletResponse resp) {
		Message message = new Message(1, null);
		String uploadPath = getServletContext().getRealPath("/upload/adv");
		File uploadPathFile = new File(uploadPath);
		if (!uploadPathFile.exists()) {
			uploadPathFile.mkdir();
		}

		// 判断提交过来的表单是否为文件上传菜单
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			// 构造一个文件上传处理对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			Iterator items;
			try {
				// 解析表单中提交的所有文件内容
				items = upload.parseRequest(req).iterator();
				int index = 0;
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();
					// 如果为文件域
					if (!item.isFormField()) {
						// 取出上传文件的文件名称
						item.getFieldName().equals("fileName");
						String name = item.getName();
						// 取得上传文件以后的存储路径
						String fileName = UUID.randomUUID().toString().replace("-", "")
								+ name.substring(name.lastIndexOf("."));
						// 上传文件以后的存储路径
						String filePath = uploadPath + File.separatorChar + fileName;
						File uploaderFile = new File(filePath);
						item.write(uploaderFile);
						message = new Message(1, fileName);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统出错，请联系管理员");
			}
		}

		Utils.IO.writeText(Utils.Json.toJson(message), resp);

		return null;
	}

	/**
	 * 增加广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("add_adv")
	public ModelAndView add_newsarticle(HttpServletRequest request,
			HttpServletResponse response) {
		final String typeCode = request.getParameter("typeCode");// 广告位类型
		final String regionCode = request.getParameter("regionCode");// 地区编码-地区名
		final String advArea = request.getParameter("advArea");// 体验馆/频道
		final String advPage = request.getParameter("advPage");// 体验馆内区域/页面地址
		final String advPosition = request.getParameter("advPosition");// 网页中具体位置
		final String imgName = request.getParameter("imgName");// 图片名
		final String advPattern = request.getParameter("advPattern");// 格式
		final String advSize = request.getParameter("advSize");// 尺寸
		final String webpageUrl = request.getParameter("webpageUrl");// 静态uri
		final String advStatus = request.getParameter("advStatus");// 状态
		final String advExplain = request.getParameter("advExplain");// 说明

		// 验证器
		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(advArea)) {
					return new Message(10, "请输入体验馆/频道!", "advArea");
				} else if (advArea.length() > 100) {
					return new Message(10, "体验馆/频道最大长度为100!", "advArea");
				}
				if (Utils.Str.isEmpty(advPage)) {
					return new Message(10, "请输入体验馆内区域/页面地址!", "advPage");
				} else if (advPage.length() > 100) {
					return new Message(10, "体验馆内区域/页面地址最大长度为100!", "advPage");
				}
				if (typeCode.equals("01")) {// 网页广告位
					if (Utils.Str.isEmpty(advPosition)) {
						return new Message(10, "请输入网页中具体位置!", "advPosition");
					} else if (advPosition.length() > 32) {
						return new Message(10, "网页中具体位置最大长度为32!", "advPosition");
					}
				}
				if (Utils.Str.isEmpty(imgName)) {
					return new Message(10, "请上传图片!", "imgName");
				} else if (imgName.length() > 100) {
					return new Message(10, "图片名最大长度为100!", "imgName");
				}
				if (!typeCode.equals("03")) {// 不是海报
					if (Utils.Str.isEmpty(advPattern)) {
						return new Message(10, "请输入格式!", "advPattern");
					} else if (advPattern.length() > 100) {
						return new Message(10, "格式最大长度为100!", "advPattern");
					}
				}
				if (Utils.Str.isEmpty(advSize)) {
					return new Message(10, "请输入广告位尺寸!", "advSize");
				} else if (advSize.length() > 32) {
					return new Message(10, "广告位尺寸字段最大长度为32!", "advSize");
				}
				if (typeCode.equals("01")) {// 网页广告位
					if (Utils.Str.isEmpty(webpageUrl)) {
						return new Message(10, "请输入静态链接url!", "webpageUrl");
					} else if (webpageUrl.length() > 64) {
						return new Message(10, "静态链接url最大长度为64!", "webpageUrl");
					}
				}
				if (Utils.Str.isEmpty(advExplain)) {
					return new Message(10, "请输入广告位说明!", "advExplain");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvAttribute advAttribute = new AdvAttribute();
				advAttribute.setRegionCode(regionCode.split("-")[0]);// 地区编码
				advAttribute.setRegionName(regionCode.split("-")[1]);// 地区名
				advAttribute.setImgName(imgName);// 图片名
				advAttribute.setAdvPattern(advPattern);// 格式
				advAttribute.setAdvSize(advSize);// 尺寸
				advAttribute.setWebpageUrl(webpageUrl);// url
				advAttribute.setAdvExplain(advExplain);// 说明
				advAttribute.setAdvStatus(Integer.parseInt(advStatus));// 状态
				advAttributeService.save(advAttribute);
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
	 * 详细信息
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_detail_adv")
	public ModelAndView initDetailAdv(HttpServletRequest request,
			HttpServletResponse resp) {
		String advId = request.getParameter("advId");// 广告位主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvAttribute advAttribute = advAttributeService.findOneByAdvId(advId);
		model.put("advAttribute", advAttribute);
		return new ModelAndView("adv/init_detail_adv", model);
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_modify_adv")
	public ModelAndView init_modify_adv(HttpServletRequest request,
			HttpServletResponse resp) {
		String advId = request.getParameter("advId");// 广告位主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvAttribute advAttribute = advAttributeService.findOneByAdvId(advId);
		model.put("advAttribute", advAttribute);
		List<Region> regionList1 = regionService.getParentRegionAll();
		List<AdvType> advTypeList = advTypeService.queryAdvTypeAll();
		String parentCode = regionService.getRegionByChildrenCode(advAttribute
				.getRegionCode());
		List<Region> regionList = new ArrayList<Region>();
		for (int i = 0; i < regionList1.size(); i++) {
			if (regionList1.get(i).getCode().equals(parentCode)) {
				regionList.add(regionList1.get(i));
				break;
			}
		}
		for (int i = 0; i < regionList1.size(); i++) {
			if (!regionList1.get(i).getCode().equals(parentCode)) {
				regionList.add(regionList1.get(i));
			}
		}
		model.put("advTypeList", advTypeList);
		model.put("regionList", regionList);
		return new ModelAndView("adv/init_modify_adv", model);
	}

	/**
	 * 修改广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("modify_adv")
	public ModelAndView modify_adv(HttpServletRequest request,
			HttpServletResponse response) {
		final String advId = request.getParameter("advId");// 广告位advId
		final String typeCode = request.getParameter("typeCode");// 广告位类型
		final String regionCode = request.getParameter("regionCode");// 地区编码-地区名
		final String advArea = request.getParameter("advArea");// 体验馆/频道
		final String advPage = request.getParameter("advPage");// 体验馆内区域/页面地址
		final String advPosition = request.getParameter("advPosition");// 网页中具体位置
		final String imgName = request.getParameter("imgName");// 图片名
		final String advPattern = request.getParameter("advPattern");// 格式
		final String advSize = request.getParameter("advSize");// 尺寸
		final String webpageUrl = request.getParameter("webpageUrl");// 静态uri
		final String advStatus = request.getParameter("advStatus");// 状态
		final String advExplain = request.getParameter("advExplain");// 说明

		// 验证器
		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(advArea)) {
					return new Message(10, "请输入体验馆/频道!", "advArea");
				} else if (advArea.length() > 100) {
					return new Message(10, "体验馆/频道最大长度为100!", "advArea");
				}
				if (Utils.Str.isEmpty(advPage)) {
					return new Message(10, "请输入体验馆内区域/页面地址!", "advPage");
				} else if (advPage.length() > 100) {
					return new Message(10, "体验馆内区域/页面地址最大长度为100!", "advPage");
				}
				if (typeCode.equals("01")) {// 网页广告位
					if (Utils.Str.isEmpty(advPosition)) {
						return new Message(10, "请输入网页中具体位置!", "advPosition");
					} else if (advPosition.length() > 32) {
						return new Message(10, "网页中具体位置最大长度为32!", "advPosition");
					}
				}
				if (!typeCode.equals("03")) {// 不是海报
					if (Utils.Str.isEmpty(advPattern)) {
						return new Message(10, "请输入格式!", "advPattern");
					} else if (advPattern.length() > 100) {
						return new Message(10, "格式最大长度为100!", "advPattern");
					}
				}
				if (Utils.Str.isEmpty(advSize)) {
					return new Message(10, "请输入广告位尺寸!", "advSize");
				} else if (advSize.length() > 32) {
					return new Message(10, "广告位尺寸字段最大长度为32!", "advSize");
				}
				if (typeCode.equals("01")) {// 网页广告位
					if (Utils.Str.isEmpty(webpageUrl)) {
						return new Message(10, "请输入静态链接url!", "webpageUrl");
					} else if (webpageUrl.length() > 64) {
						return new Message(10, "静态链接url最大长度为64!", "webpageUrl");
					}
				}
				if (Utils.Str.isEmpty(advExplain)) {
					return new Message(10, "请输入广告位说明!", "advExplain");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvAttribute advAttribute = new AdvAttribute();
				advAttribute.setAdvId(Integer.parseInt(advId));
				advAttribute.setRegionCode(regionCode.split("-")[0]);// 地区编码
				advAttribute.setRegionName(regionCode.split("-")[1]);// 地区名
				if (Utils.Str.isEmpty(imgName)) {
					advAttribute.setImgName(advAttributeService.findOneByAdvId(advId)
							.getImgName());// 图片名
				} else {
					advAttribute.setImgName(imgName);// 图片名
				}
				advAttribute.setAdvPattern(advPattern);// 格式
				advAttribute.setAdvSize(advSize);// 尺寸
				advAttribute.setWebpageUrl(webpageUrl);// url
				advAttribute.setAdvExplain(advExplain);// 说明
				advAttribute.setAdvStatus(Integer.parseInt(advStatus));// 状态
				advAttributeService.modify(advAttribute);
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
	 * 上传帮助图片
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("upload_adv_image")
	public void initUploadAdvImage(HttpServletRequest req,
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

	/**
	 * 删除广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("remove_adv")
	public ModelAndView remove_adv(HttpServletRequest request,
			HttpServletResponse response) {
		final String advIds = request.getParameter("advIds");// 广告位ids
		Message message = null;
		try {
			advAttributeService.delete(advIds);
			message = new Message(1, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "删除失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}

	/**
	 * 初始化广告位查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_add_winadvadd")
	public ModelAndView init_add_winadvadd(HttpServletRequest req,
			HttpServletResponse resp) {
		String id = req.getParameter("id");// 1是广告位发布，2是广告发布
		List<Region> regionList = regionService.getParentRegionAll();
		Map<String, Object> model = new HashMap<String, Object>();
		List<AdvType> advTypeList = advTypeService.queryAdvTypeAll();
		model.put("advTypeList", advTypeList);
		model.put("regionList", regionList);
		model.put("tyId", id);
		return new ModelAndView("advrelease/init_add_winadvadd", model);
	}

	/**
	 * 条件查询广告位信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_advadd_list")
	public ModelAndView query_advadd_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvAttributeParam advAttributeParam = new AdvAttributeParam(
					this.initPage(request));// 分页条件
			String typeCode = request.getParameter("typeCode");// 类型编码
			String code = request.getParameter("code");// 地区编码
			if (!Utils.Str.isEmpty(typeCode)) {
				advAttributeParam.setTypeCode(typeCode);// 设置类型编码
			}
			if (!Utils.Str.isEmpty(code)) {
				advAttributeParam.setCode(code);// 设置地区编码
			}
			advAttributeParam.setAdvStatus("0");// 设置状态
			Map<String, Object> model = new HashMap<String, Object>();
			int total = advAttributeService.queryCount(advAttributeParam);
			model.put("total", total);
			advAttributeParam.getPagination().setPage(0);
			advAttributeParam.getPagination().setRows(total);
			model
					.put("rows", advAttributeService.queryAllpageList(advAttributeParam));
			this.htmlWrite(response, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改广告位状态
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
		final String advStatus = request.getParameter("advStatus");// 广告位id
		Message message = null;
		try {
			AdvAttribute advAttribute = new AdvAttribute();
			advAttribute.setAdvId(Integer.parseInt(advId));
			advAttribute.setAdvStatus(Integer.parseInt(advStatus));
			advAttributeService.updateAdvStatus(advAttribute);
			message = new Message(1, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "修改失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}

}
