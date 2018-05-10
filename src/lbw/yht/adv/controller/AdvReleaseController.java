package lbw.yht.adv.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import lbw.yht.adv.domain.AdvRelRrelease;
import lbw.yht.adv.domain.AdvRelease;
import lbw.yht.adv.domain.ReleaseTime;
import lbw.yht.adv.domain.param.AdvReleaseParam;
import lbw.yht.adv.service.AdvRelRreleaseService;
import lbw.yht.adv.service.AdvReleaseService;
import lbw.yht.adv.service.ReleaseTimeService;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("advrelease")
public class AdvReleaseController extends BaseController {

	@Autowired
	private AdvReleaseService advReleaseService;// 发布
	@Autowired
	private AdvRelRreleaseService advRelRreleaseService;// 发布-广告位中间
	@Autowired
	private ReleaseTimeService releaseTimeService;// 发布时间段

	/**
	 * 初始化发布广告记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_advrelease")
	public ModelAndView init_advrelease(HttpServletRequest req,
			HttpServletResponse resp) {
		return new ModelAndView("advrelease/init_advrelease");
	}

	/**
	 * 条件查询广告发布信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_advrelease_list")
	public ModelAndView query_advrelease_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvReleaseParam advReleaseParam = new AdvReleaseParam(
					this.initPage(request));// 分页条件

			String beginDateS = request.getParameter("beginDateS");//
			String endDateS = request.getParameter("endDateS");//
			String beginDateE = request.getParameter("beginDateE");//
			String endDateE = request.getParameter("endDateE");//

			if (!Utils.Str.isEmpty(beginDateS) || !Utils.Str.isEmpty(endDateS)) {
				if (!Utils.Str.isEmpty(beginDateS)) {// 开始时间不为空
					beginDateS = beginDateS.trim();
				}
				if (!Utils.Str.isEmpty(endDateS)) {// 结束时间不为空
					endDateS = endDateS.trim();
				}
				if (Utils.Str.isDate(beginDateS)) {// 开始时间是日期类型
					advReleaseParam.setBeginDateS(beginDateS);//
				}
				if (Utils.Str.isDate(endDateS)) {// 结束时间是日期类型
					advReleaseParam.setEndDateS(endDateS);
				}
				if (advReleaseParam.getBeginDateS() == null
						&& advReleaseParam.getEndDateS() != null) {// 如果开始时间为null结束时间存在
					advReleaseParam.setBeginDateS("1000-01-01");//
				} else if (advReleaseParam.getBeginDateS() != null
						&& advReleaseParam.getEndDateS() == null) {
					advReleaseParam.setEndDateS("9999-12-31");
				}
			}

			if (!Utils.Str.isEmpty(beginDateE) || !Utils.Str.isEmpty(endDateE)) {
				if (!Utils.Str.isEmpty(beginDateE)) {// 开始时间不为空
					beginDateE = beginDateE.trim();
				}
				if (!Utils.Str.isEmpty(endDateE)) {// 结束时间不为空
					endDateE = endDateE.trim();
				}
				if (Utils.Str.isDate(beginDateE)) {// 开始时间是日期类型
					advReleaseParam.setBeginDateE(beginDateE);//
				}
				if (Utils.Str.isDate(endDateE)) {// 结束时间是日期类型
					advReleaseParam.setEndDateE(endDateE);
				}
				if (advReleaseParam.getBeginDateE() == null
						&& advReleaseParam.getEndDateE() != null) {// 如果开始时间为null结束时间存在
					advReleaseParam.setBeginDateE("1000-01-01");//
				} else if (advReleaseParam.getBeginDateE() != null
						&& advReleaseParam.getEndDateE() == null) {
					advReleaseParam.setEndDateE("9999-12-31");
				}

			}

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", advReleaseService.queryCount(advReleaseParam));
			model.put("rows", advReleaseService.queryAllpageList(advReleaseParam));
			this.htmlWrite(response, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 详细信息
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_detail_advrelease")
	public ModelAndView init_detail_advrelease(HttpServletRequest request,
			HttpServletResponse resp) {
		String releaseId = request.getParameter("releaseId");// 广告发布主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvRelease advRelease = advReleaseService.findByReleaseId(releaseId);
		model.put("advRelease", advRelease);
		return new ModelAndView("advrelease/init_detail_advrelease", model);
	}

	/**
	 * 初始化增加发布广告查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_add_advrelease")
	public ModelAndView init_add_advrelease(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("advrelease/init_add_advrelease", model);
	}

	/**
	 * 增加
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("add_advrelease")
	public ModelAndView add_advrelease(HttpServletRequest request,
			HttpServletResponse response) {
		final String releaseName = request.getParameter("releaseName").trim();// 发布名
		String biddingId = request.getParameter("biddingId");// 竞价id
		final String startDate = request.getParameter("startDate");// 开始时间
		final String endDate = request.getParameter("endDate");// 结束时间
		final String releaseContent = request.getParameter("releaseContent");// 发布说明

		final String advId = request.getParameter("advId");// 广告位 ""-""-""-

		final String startTime = request.getParameter("startTime");// 播放开始时间""-""-""-
		final String endTime = request.getParameter("endTime");// 播放结束时间 ""-""-""-
		final String totalNumber = request.getParameter("totalNumber");// 播放次数

		if ("".equals(biddingId.trim())) {
			biddingId = null;
		}

		String[] advIds = advId.split("-");// 广告位
		String[] startTimes = null;
		String[] endTimes = null;
		String[] totalNumbers = null;
		if (startTime != "") {
			startTimes = startTime.split("-");// 播放开始时间
			endTimes = endTime.split("-");// 播放结束时间
			totalNumbers = totalNumber.split("-");// 播放次数
		}
		// 验证器
		class Validator {
			public Message validate() throws ParseException {
				if (Utils.Str.isEmpty(releaseName)) {
					return new Message(10, "请输入广告发布名!", "releaseName");
				} else if (releaseName.length() > 100) {
					return new Message(10, "广告发布名最大长度为100!", "releaseName");
				}
				if (!Utils.Str.isDate(startDate)) {
					return new Message(10, "请输入正确的开始展示时间!", "startDate");
				}
				if (!Utils.Str.isDate(endDate)) {
					return new Message(10, "请输入正确的结束展示时间!", "endDate");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = df.parse(startDate);
				Date dt2 = df.parse(endDate);
				if (dt1.getTime() > dt2.getTime()) {
					return new Message(10, "结束展示时间应该晚于开始展示时间!", "startDate");
				}
				if (Utils.Str.isEmpty(advId)) {
					return new Message(10, "请选择要发布的广告位!", "advId");
				}
				if (Utils.Str.isEmpty(releaseContent)) {
					return new Message(10, "请输入发布说明!", "releaseContent");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = null;
		try {
			message = validator.validate();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (message.getCode() == 1) {
			try {

				boolean boo = true;
				if (startTimes != null) {
					for (int i = 0; i < startTimes.length; i++) {
						String[] dt1 = startTimes[i].split(":");// 开始时间
						String[] dt2 = endTimes[i].split(":");// 结束时间
						long beTi = Integer.parseInt(dt1[0]) * 60 * 60
								+ Integer.parseInt(dt1[1]) * 60 + Integer.parseInt(dt1[2]);
						long enTi = Integer.parseInt(dt2[0]) * 60 * 60
								+ Integer.parseInt(dt2[1]) * 60 + Integer.parseInt(dt2[2]);
						if (beTi > enTi) {
							message = new Message(10, "播放结束时间应该晚于播放开始时间!", "");
							boo = false;
							break;
						}
					}
				}
				if (boo) {
					List<AdvRelease> AdvReleaseold = advReleaseService
							.findByReleaseName(releaseName);// 通过发布名找记录
					if (AdvReleaseold == null || AdvReleaseold.size() == 0) {// 没有重复的发布名
						AdvRelease advRelease = new AdvRelease();// 广告发布对象
						advRelease.setReleaseName(releaseName.trim());// 广告发布名
						advRelease.setBiddingId(biddingId);// 竞价id
						advRelease.setStartDate(startDate);// 开始使用时间
						advRelease.setEndDate(endDate);// 结束使用时间
						advRelease.setReleaseContent(releaseContent);// 发布说明
						advReleaseService.save(advRelease);// 保存

						advRelease = advReleaseService.findByReleaseName(releaseName)
								.get(0);// 找记录
						AdvRelRrelease advRelRrelease = new AdvRelRrelease();// 广告发布记录
						advRelRrelease.setReleaseId(advRelease.getReleaseId().toString());// 发布记录id

						for (int i = 0; i < advIds.length; i++) {// 插入广告位发布记录
							advRelRrelease.setAdvId(advIds[i]);
							advRelRreleaseService.save(advRelRrelease);// 插入第三张表记录
						}

						if (startTimes != null) {
							ReleaseTime releaseTime = new ReleaseTime();// 发布时间段
							releaseTime.setReleaseId(advRelease.getReleaseId().toString()); // 发布记录id
							for (int i = 0; i < startTimes.length; i++) {
								releaseTime.setStartTime(startTimes[i]);// 开始时间段
								releaseTime.setEndTime(endTimes[i]);// 结束时间段
								releaseTime.setTotalNumber(totalNumbers[i]);// 播放次数
								releaseTimeService.save(releaseTime);// 保存记录
							}
						}
						message = new Message(1, "");
					} else {// 发布名存在
						message = new Message(10, "广告发布名已存在!", "releaseName");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "新增失败!");
			}
		}
		this.htmlWrite(response, message);
		return null;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_modify_advrelease")
	public ModelAndView init_modify_advrelease(HttpServletRequest request,
			HttpServletResponse resp) {
		String releaseId = request.getParameter("releaseId");// 广告发布id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvRelease advRelease = advReleaseService.findByReleaseId(releaseId);// 通过
		model.put("advRelease", advRelease);
		return new ModelAndView("advrelease/init_modify_advrelease", model);
	}

	/**
	 * 修改发布信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("modify_advrelease")
	public ModelAndView modify_advrelease(HttpServletRequest request,
			HttpServletResponse response) {
		final String releaseId = request.getParameter("releaseId");// 发布id
		final String releaseName = request.getParameter("releaseName").trim();// 发布名
		String biddingId = request.getParameter("biddingId");// 竞价id
		final String startDate = request.getParameter("startDate");// 开始时间
		final String endDate = request.getParameter("endDate");// 结束时间
		final String releaseContent = request.getParameter("releaseContent");// 发布说明

		final String advId = request.getParameter("advId");// 广告位 ""-""-""-

		final String startTime = request.getParameter("startTime");// 播放开始时间""-""-""-
		final String endTime = request.getParameter("endTime");// 播放结束时间 ""-""-""-
		final String totalNumber = request.getParameter("totalNumber");// 播放次数

		if ("".equals(biddingId.trim())) {
			biddingId = null;
		}

		String[] advIds = advId.split("-");// 广告位
		String[] startTimes = null;
		String[] endTimes = null;
		String[] totalNumbers = null;
		if (startTime != "") {
			startTimes = startTime.split("-");// 播放开始时间
			endTimes = endTime.split("-");// 播放结束时间
			totalNumbers = totalNumber.split("-");// 播放次数
		}
		// 验证器
		class Validator {
			public Message validate() throws ParseException {
				if (Utils.Str.isEmpty(releaseName)) {
					return new Message(10, "请输入广告发布名!", "releaseName");
				} else if (releaseName.length() > 100) {
					return new Message(10, "广告发布名最大长度为100!", "releaseName");
				}
				if (!Utils.Str.isDate(startDate)) {
					return new Message(10, "请输入正确的开始展示时间!", "startDate");
				}
				if (!Utils.Str.isDate(endDate)) {
					return new Message(10, "请输入正确的结束展示时间!", "endDate");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = df.parse(startDate);
				Date dt2 = df.parse(endDate);
				if (dt1.getTime() > dt2.getTime()) {
					return new Message(10, "结束展示时间应该晚于开始展示时间!", "startDate");
				}
				if (Utils.Str.isEmpty(advId)) {
					return new Message(10, "请选择要发布的广告位!", "advId");
				}
				if (Utils.Str.isEmpty(releaseContent)) {
					return new Message(10, "请输入发布说明!", "releaseContent");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = null;
		try {
			message = validator.validate();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (message.getCode() == 1) {
			try {

				boolean boo = true;
				if (startTimes != null) {
					for (int i = 0; i < startTimes.length; i++) {
						String[] dt1 = startTimes[i].split(":");// 开始时间
						String[] dt2 = endTimes[i].split(":");// 结束时间
						long beTi = Integer.parseInt(dt1[0]) * 60 * 60
								+ Integer.parseInt(dt1[1]) * 60 + Integer.parseInt(dt1[2]);
						long enTi = Integer.parseInt(dt2[0]) * 60 * 60
								+ Integer.parseInt(dt2[1]) * 60 + Integer.parseInt(dt2[2]);
						if (beTi > enTi) {
							message = new Message(10, "播放结束时间应该晚于播放开始时间!", "");
							boo = false;
							break;
						}
					}
				}
				if (boo) {
					List<AdvRelease> AdvReleaseold = advReleaseService
							.findByReleaseName(releaseName);// 通过发布名找记录
					if (AdvReleaseold != null && AdvReleaseold.size() != 0) {
						if (AdvReleaseold.get(0).getReleaseId() == Integer
								.parseInt(releaseId)) {// 发布记录是自己
							AdvReleaseold = null;
						}
					}
					if (AdvReleaseold == null || AdvReleaseold.size() == 0) {// 没有重复的发布名
						AdvRelease advRelease = new AdvRelease();// 广告发布对象
						advRelease.setReleaseId(Integer.parseInt(releaseId));// 广告发布id
						advRelease.setReleaseName(releaseName.trim());// 广告发布名
						advRelease.setBiddingId(biddingId);// 竞价id
						advRelease.setStartDate(startDate);// 开始使用时间
						advRelease.setEndDate(endDate);// 结束使用时间
						advRelease.setReleaseContent(releaseContent);// 发布说明
						advReleaseService.modify(advRelease);// 修改记录

						advRelRreleaseService.deleteByReleaseId(releaseId);// 通过发布id删除广告位发布记录
						AdvRelRrelease advRelRrelease = new AdvRelRrelease();// 广告发布记录
						advRelRrelease.setReleaseId(releaseId);// 发布记录id
						for (int i = 0; i < advIds.length; i++) {// 插入广告位发布记录
							advRelRrelease.setAdvId(advIds[i]);
							advRelRreleaseService.save(advRelRrelease);// 插入第三张表记录
						}

						releaseTimeService.deleteByReleaseId(releaseId);// 通过发布id删除播放时间段记录
						if (startTimes != null) {
							ReleaseTime releaseTime = new ReleaseTime();// 发布时间段
							releaseTime.setReleaseId(advRelease.getReleaseId().toString()); // 发布记录id
							for (int i = 0; i < startTimes.length; i++) {
								releaseTime.setStartTime(startTimes[i]);// 开始时间段
								releaseTime.setEndTime(endTimes[i]);// 结束时间段
								releaseTime.setTotalNumber(totalNumbers[i]);// 播放次数
								releaseTimeService.save(releaseTime);// 保存记录
							}
						}
						message = new Message(1, "修改成功!");
					} else {// 发布名存在
						message = new Message(10, "广告发布名已存在!", "releaseName");
					}

				}
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
	@RequestMapping("upload_advrelease_image")
	public void upload_advrelease_image(HttpServletRequest req,
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
								+ File.separatorChar + "advrelease_img" + File.separatorChar
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

							relativeUrl = "../upload/advrelease_img/" + typeName + "/"
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
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("remove_advrelease")
	public ModelAndView remove_advrelease(HttpServletRequest request,
			HttpServletResponse response) {
		final String releaseId = request.getParameter("releaseId");// 广告发布id
		Message message = null;
		try {
			advReleaseService.deleteByReleaseId(releaseId);// 发布
			advRelRreleaseService.deleteByReleaseId(releaseId);// 发布-广告位中间
			releaseTimeService.deleteByReleaseId(releaseId);// 发布时间段
			message = new Message(1, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "删除失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}

}
