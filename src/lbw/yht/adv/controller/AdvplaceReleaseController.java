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
import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.AdvplaceRelease;
import lbw.yht.adv.domain.param.AdvplaceReleaseParam;
import lbw.yht.adv.service.AdvTypeService;
import lbw.yht.adv.service.AdvplaceReleaseService;
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
@RequestMapping("advposire")
public class AdvplaceReleaseController extends BaseController {

	@Autowired
	private AdvplaceReleaseService advplaceReleaseService;// 广告位发布
	@Autowired
	private AdvTypeService advTypeService;// 广告位类型

	/**
	 * 初始化广告位发布记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_advposire")
	public ModelAndView init_advposire(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<AdvType> advTypeList = advTypeService.queryAdvTypeAll();
		model.put("advTypeList", advTypeList);
		return new ModelAndView("advposire/init_advposire", model);
	}

	/**
	 * 条件查询广告位发布列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_advplace_list")
	public ModelAndView query_advplace_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvplaceReleaseParam advplaceReleaseParam = new AdvplaceReleaseParam(
					this.initPage(request));// 分页条件
			String beginDateS = request.getParameter("beginDateS");//
			String endDateS = request.getParameter("endDateS");//
			String typeCode = request.getParameter("typeCode");// 广告位类型
			String advplaceState = request.getParameter("advplaceState");// 发布状态

			if (!Utils.Str.isEmpty(beginDateS) || !Utils.Str.isEmpty(endDateS)) {
				if (!Utils.Str.isEmpty(beginDateS)) {// 开始时间不为空
					beginDateS = beginDateS.trim();
				}
				if (!Utils.Str.isEmpty(endDateS)) {// 结束时间不为空
					endDateS = endDateS.trim();
				}
				if (Utils.Str.isDate(beginDateS)) {// 开始时间是日期类型
					advplaceReleaseParam.setBeginDateS(beginDateS);//
				}
				if (Utils.Str.isDate(endDateS)) {// 结束时间是日期类型
					advplaceReleaseParam.setEndDateS(endDateS);
				}
				if (advplaceReleaseParam.getBeginDateS() == null
						&& advplaceReleaseParam.getEndDateS() != null) {// 如果开始时间为null结束时间存在
					advplaceReleaseParam.setBeginDateS("1000-01-01");//
				} else if (advplaceReleaseParam.getBeginDateS() != null
						&& advplaceReleaseParam.getEndDateS() == null) {
					advplaceReleaseParam.setEndDateS("9999-12-31");
				}
			}
			if (!Utils.Str.isEmpty(typeCode)) {
				advplaceReleaseParam.setTypeCode(typeCode);
			}
			if (!Utils.Str.isEmpty(advplaceState)) {
				advplaceReleaseParam.setAdvplaceState(advplaceState);
			}

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total",
					advplaceReleaseService.queryCount(advplaceReleaseParam));
			model.put("rows",
					advplaceReleaseService.queryAllpageList(advplaceReleaseParam));
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
	@RequestMapping("init_detail_advplace")
	public ModelAndView init_detail_advplace(HttpServletRequest request,
			HttpServletResponse resp) {
		String advplaceId = request.getParameter("advplaceId");// 广告位发布主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvplaceRelease advplaceRelease = advplaceReleaseService
				.findByAdvplaceId(advplaceId);
		model.put("advplaceRelease", advplaceRelease);
		return new ModelAndView("advposire/init_detail_advplace", model);
	}

	/**
	 * 初始化增加广告位发布
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_add_advposire")
	public ModelAndView init_add_advposire(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("advposire/init_add_advposire", model);
	}

	/**
	 * 增加
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("add_advposire")
	public ModelAndView add_advposire(HttpServletRequest request,
			HttpServletResponse response) {
		final String advId = request.getParameter("advId").trim();// 广告位id
		final String startingPrice = request.getParameter("startingPrice");// 起拍价
		final String increase = request.getParameter("increase");// 幅度
		final String assessmentPrice = request.getParameter("assessmentPrice");// 评估价
		final String startBiddingPeriod = request
				.getParameter("startBiddingPeriod");// 开始竞拍时间
		final String endBiddingPeriod = request.getParameter("endBiddingPeriod");// 结束竞拍时间
		final String startUseTime = request.getParameter("startUseTime");// 开始展示时间
		final String endUseTime = request.getParameter("endUseTime");// 结束展示时间
		final String biddingNotice = request.getParameter("biddingNotice");//
		final String biddingBulletin = request.getParameter("biddingBulletin");//
		// 验证器
		class Validator {
			public Message validate() throws ParseException {
				if (Utils.Str.isEmpty(advId)) {
					return new Message(10, "请选择广告位!", "advId");
				}
				if (Utils.Str.isEmpty(startingPrice)) {
					return new Message(10, "请输入起拍价!", "startingPrice");
				} else if (startingPrice.length() > 10) {
					return new Message(10, "起拍价最大长度为10个字符!", "startingPrice");
				}
				if (Utils.Str.isEmpty(increase)) {
					return new Message(10, "请输入加价幅度!", "increase");
				} else if (increase.length() > 10) {
					return new Message(10, "加价幅度最大长度为5个字符!", "increase");
				}
				if (Utils.Str.isEmpty(assessmentPrice)) {
					return new Message(10, "请输入评估价!", "assessmentPrice");
				} else if (assessmentPrice.length() > 10) {
					return new Message(10, "评估价最大长度为5个字符!", "assessmentPrice");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = df.parse(startBiddingPeriod);
				Date dt2 = df.parse(endBiddingPeriod);
				Date dt3 = df.parse(startUseTime);
				Date dt4 = df.parse(endUseTime);
				if (dt1.getTime() > dt2.getTime()) {
					return new Message(10, "结束竞拍时间应该晚于开始竞拍时间!", "startBiddingPeriod");
				}
				if (dt3.getTime() > dt4.getTime()) {
					return new Message(10, "结束展示时间应该晚于开始展示时间!", "startUseTime");
				}
				if (dt2.getTime() > dt3.getTime()) {
					return new Message(10, "开始展示时间应该晚于结束竞拍时间!", "startUseTime");
				}
				if (Utils.Str.isEmpty(biddingNotice)) {
					return new Message(10, "请输入竞价须知!", "biddingNotice");
				}
				if (Utils.Str.isEmpty(biddingBulletin)) {
					return new Message(10, "请输入竞拍公告!", "biddingBulletin");
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
				AdvplaceRelease advplaceRelease = new AdvplaceRelease();
				AdvAttribute advAttribute = new AdvAttribute();
				advAttribute.setAdvId(Integer.parseInt(advId));// 广告位id
				advplaceRelease.setAdvAttribute(advAttribute);
				advplaceRelease.setStartingPrice(startingPrice);// 起拍价
				advplaceRelease.setIncrease(Integer.parseInt(increase));
				advplaceRelease.setAssessmentPrice(assessmentPrice);
				advplaceRelease.setStartBiddingPeriod(startBiddingPeriod);
				advplaceRelease.setEndBiddingPeriod(endBiddingPeriod);
				advplaceRelease.setStartUseTime(startUseTime);
				advplaceRelease.setEndUseTime(endUseTime);
				advplaceRelease.setBiddingNotice(biddingNotice);
				advplaceRelease.setBiddingBulletin(biddingBulletin);
				advplaceRelease.setAdvplaceState(0);// 竞拍中

				List<AdvplaceRelease> AdvplaceReleold = advplaceReleaseService
						.findByDate(advplaceRelease);// 通过advId、开始使用时间、结束使用时间找没有结束的发布记录
				if (AdvplaceReleold == null || AdvplaceReleold.size() == 0) {//
					advplaceReleaseService.save(advplaceRelease);
					message = new Message(1, "");
				} else {// 发布名存在
					message = new Message(10, "该广告在该展示时段已经发布!");
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
	@RequestMapping("init_modify_advposire")
	public ModelAndView init_modify_advposire(HttpServletRequest request,
			HttpServletResponse resp) {
		String advplaceId = request.getParameter("advplaceId");// 广告位发布主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvplaceRelease advplaceRelease = advplaceReleaseService
				.findByAdvplaceId(advplaceId);
		model.put("advplaceRelease", advplaceRelease);
		return new ModelAndView("advposire/init_modify_advposire", model);
	}

	/**
	 * 修改发布信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("modify_advposire")
	public ModelAndView modify_advposire(HttpServletRequest request,
			HttpServletResponse response) {
		final String advplaceId = request.getParameter("advplaceId");
		final String advId = request.getParameter("advId").trim();// 广告位id
		final String startingPrice = request.getParameter("startingPrice");// 起拍价
		final String increase = request.getParameter("increase");// 幅度
		final String assessmentPrice = request.getParameter("assessmentPrice");// 评估价
		final String startBiddingPeriod = request
				.getParameter("startBiddingPeriod");// 开始竞拍时间
		final String endBiddingPeriod = request.getParameter("endBiddingPeriod");// 结束竞拍时间
		final String startUseTime = request.getParameter("startUseTime");// 开始展示时间
		final String endUseTime = request.getParameter("endUseTime");// 结束展示时间
		final String biddingNotice = request.getParameter("biddingNotice");//
		final String biddingBulletin = request.getParameter("biddingBulletin");//
		// 验证器
		class Validator {
			public Message validate() throws ParseException {
				if (Utils.Str.isEmpty(advId)) {
					return new Message(10, "请选择广告位!", "advId");
				}
				if (Utils.Str.isEmpty(startingPrice)) {
					return new Message(10, "请输入起拍价!", "startingPrice");
				} else if (startingPrice.length() > 10) {
					return new Message(10, "起拍价最大长度为10个字符!", "startingPrice");
				}
				if (Utils.Str.isEmpty(increase)) {
					return new Message(10, "请输入加价幅度!", "increase");
				} else if (increase.length() > 10) {
					return new Message(10, "加价幅度最大长度为5个字符!", "increase");
				}
				if (Utils.Str.isEmpty(assessmentPrice)) {
					return new Message(10, "请输入评估价!", "assessmentPrice");
				} else if (assessmentPrice.length() > 10) {
					return new Message(10, "评估价最大长度为5个字符!", "assessmentPrice");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = df.parse(startBiddingPeriod);
				Date dt2 = df.parse(endBiddingPeriod);
				Date dt3 = df.parse(startUseTime);
				Date dt4 = df.parse(endUseTime);
				if (dt1.getTime() > dt2.getTime()) {
					return new Message(10, "结束竞拍时间应该晚于开始竞拍时间!", "startBiddingPeriod");
				}
				if (dt3.getTime() > dt4.getTime()) {
					return new Message(10, "结束展示时间应该晚于开始展示时间!", "startUseTime");
				}
				if (dt2.getTime() > dt3.getTime()) {
					return new Message(10, "开始展示时间应该晚于结束竞拍时间!", "startUseTime");
				}
				if (Utils.Str.isEmpty(biddingNotice)) {
					return new Message(10, "请输入竞价须知!", "biddingNotice");
				}
				if (Utils.Str.isEmpty(biddingBulletin)) {
					return new Message(10, "请输入竞拍公告!", "biddingBulletin");
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
				AdvplaceRelease advplaceRelease = new AdvplaceRelease();
				advplaceRelease.setAdvplaceId(Integer.parseInt(advplaceId));
				AdvAttribute advAttribute = new AdvAttribute();
				advAttribute.setAdvId(Integer.parseInt(advId));// 广告位id
				advplaceRelease.setAdvAttribute(advAttribute);
				advplaceRelease.setStartingPrice(startingPrice);// 起拍价
				advplaceRelease.setIncrease(Integer.parseInt(increase));
				advplaceRelease.setAssessmentPrice(assessmentPrice);
				advplaceRelease.setStartBiddingPeriod(startBiddingPeriod);
				advplaceRelease.setEndBiddingPeriod(endBiddingPeriod);
				advplaceRelease.setStartUseTime(startUseTime);
				advplaceRelease.setEndUseTime(endUseTime);
				advplaceRelease.setBiddingNotice(biddingNotice);
				advplaceRelease.setBiddingBulletin(biddingBulletin);
				advplaceRelease.setAdvplaceState(0);// 竞拍中

				List<AdvplaceRelease> AdvplaceReleold = advplaceReleaseService
						.findByDate(advplaceRelease);// 通过advId、开始使用时间、结束使用时间找没有结束的发布记录
				if (AdvplaceReleold != null && AdvplaceReleold.size() != 0) {
					AdvplaceRelease advplaceRelease3 = null;
					for (AdvplaceRelease advplaceRelease2 : AdvplaceReleold) {
						if (advplaceRelease2.getAdvplaceId().equals(
								advplaceRelease.getAdvplaceId())) {
							advplaceRelease3 = advplaceRelease2;
							break;
						}
					}
					if (advplaceRelease3 != null) {
						AdvplaceReleold.remove(advplaceRelease3);
					}
				}

				if (AdvplaceReleold == null || AdvplaceReleold.size() == 0) {//
					advplaceReleaseService.modify(advplaceRelease);
					message = new Message(1, "");
				} else {// 发布名存在
					message = new Message(10, "该广告在该展示时段已经发布!");
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
	@RequestMapping("upload_advposire_image")
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
								+ File.separatorChar + "advposire_img" + File.separatorChar
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

							relativeUrl = "../upload/advposire_img/" + typeName + "/"
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

	// /**
	// * 删除
	// *
	// * @param request
	// * @param response
	// * @return
	// */
	// @Transactional
	// @RequestMapping("remove_advrelease")
	// public ModelAndView remove_advrelease(HttpServletRequest request,
	// HttpServletResponse response) {
	// final String releaseId = request.getParameter("releaseId");// 广告发布id
	// Message message = null;
	// try {
	// advReleaseService.deleteByReleaseId(releaseId);// 发布
	// advRelRreleaseService.deleteByReleaseId(releaseId);// 发布-广告位中间
	// releaseTimeService.deleteByReleaseId(releaseId);// 发布时间段
	// message = new Message(1, "删除成功！");
	// } catch (Exception e) {
	// e.printStackTrace();
	// message = new Message(9, "删除失败!");
	// }
	// this.htmlWrite(response, message);
	// return null;
	// }

}
