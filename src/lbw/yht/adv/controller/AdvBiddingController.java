package lbw.yht.adv.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.AdvBidding;
import lbw.yht.adv.domain.AdvplaceRelease;
import lbw.yht.adv.domain.param.AdvBiddingParam;
import lbw.yht.adv.service.AdvBiddingService;
import lbw.yht.adv.service.AdvplaceReleaseService;
import lbw.yht.util.Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
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
@RequestMapping("advbidding")
public class AdvBiddingController extends BaseController {

	@Autowired
	private AdvBiddingService advBiddingService;// 竞价主表
	@Autowired
	private AdvplaceReleaseService advplaceReleaseService;// 广告位发布

	/**
	 * 初始化竞价记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_advbidding")
	public ModelAndView init_advrelease(HttpServletRequest req,
			HttpServletResponse resp) {
		return new ModelAndView("advbidding/init_advbidding");
	}

	/**
	 * 条件查询广告竞价信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_advbidding_list")
	public ModelAndView query_advbidding_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvBiddingParam advBiddingParam = new AdvBiddingParam(
					this.initPage(request));// 分页条件
			String biddingStatus = request.getParameter("biddingStatus");// 竞价状态

			if (!Utils.Str.isEmpty(biddingStatus)) {
				advBiddingParam.setBiddingStatus(biddingStatus);// 竞价状态
			}

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", advBiddingService.queryCount(advBiddingParam));
			model.put("rows", advBiddingService.queryAllpageList(advBiddingParam));
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
	@RequestMapping("init_detail_advbidding")
	public ModelAndView init_detail_advbidding(HttpServletRequest request,
			HttpServletResponse resp) {
		String biddingId = request.getParameter("biddingId");// 广告竞价id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvBidding advbidding = advBiddingService.findByBiddingId(biddingId);
		model.put("advBidding", advbidding);
		return new ModelAndView("advbidding/init_detail_advbidding", model);
	}

	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("download_advbidding_file")
	public ModelAndView download_advbidding_file(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String fileName = request.getParameter("fileName");// 文件名
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ fileName);
			File file = new File(request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload"
					+ File.separatorChar
					+ "advbidding"
					+ File.separatorChar
					+ new String(fileName.getBytes("ISO-8859-1"), "utf-8"));
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 合同
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_modify_advbidding")
	public ModelAndView init_modify_advbidding(HttpServletRequest request,
			HttpServletResponse resp) {
		String biddingId = request.getParameter("biddingId");// 广告竞价id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvBidding advbidding = advBiddingService.findByBiddingId(biddingId);
		model.put("advBidding", advbidding);
		return new ModelAndView("advbidding/init_modify_advbidding", model);
	}

	/**
	 * 修改合同
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("modify_advbidding")
	public ModelAndView modify_advbidding(HttpServletRequest request,
			HttpServletResponse response) {
		final String biddingId = request.getParameter("biddingId");// 竞价id
		final String agreementNo = request.getParameter("agreementNo");// 合同编号
		final String agreementName = request.getParameter("agreementName");// 合同名称

		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(agreementNo)) {
					return new Message(10, "请输入合同编号!", "agreementNo");
				} else if (agreementNo.length() > 32) {
					return new Message(10, "合同编号最大长度为32!", "agreementNo");
				}
				if (Utils.Str.isEmpty(agreementName)) {
					return new Message(10, "请输入合同名称!", "agreementName");
				} else if (agreementName.length() > 32) {
					return new Message(10, "合同名称最大长度为32!", "agreementName");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvBidding advBidding = new AdvBidding();
				advBidding.setBiddingId(Integer.parseInt(biddingId));
				advBidding.setAgreementNo(agreementNo.trim());
				advBidding.setAgreementName(agreementName.trim());
				advBiddingService.modifyAgreement(advBidding);
				message = new Message(1, "合同修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "合同修改失败!");
			}
		}
		this.htmlWrite(response, message);
		return null;

	}

	/**
	 * 竞价状态修改
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@Transactional
	@RequestMapping("init_modify_advbiddingStatus")
	public ModelAndView init_modify_advbiddingStatus(HttpServletRequest request,
			HttpServletResponse resp) {
		String biddingStatus = request.getParameter("biddingStatus");// 广告竞价状态
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("biddingStatus", biddingStatus);
		return new ModelAndView("advbidding/init_modify_advbiddingStatus", model);
	}

	@RequestMapping("modify_advbiddingStatus")
	public ModelAndView modify_advbiddingStatus(HttpServletRequest request,
			HttpServletResponse response) {
		Message message = new Message(1, "操作成功");
		String ids = request.getParameter("ids");
		String biddingStatus = request.getParameter("biddingStatus");
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (biddingStatus.equals("1")) {// 竞价成功
				String[] biddingIds = ids.split(",");
				boolean boo = true;
				for (int i = 0; i < biddingIds.length; i++) {
					AdvBidding advBiddingOld = advBiddingService
							.findByBiddingId(biddingIds[i]);// 获取到竞价信息
					List<AdvplaceRelease> advplaceReleaseList = advBiddingOld
							.getAdvplaceReleaseList();// 广告位发布列表

					if (advplaceReleaseList != null && advplaceReleaseList.size() > 0) {// 判断是否可以设置为竞拍成功

						for (AdvplaceRelease advplaceRelease : advplaceReleaseList) {

							AdvAttribute advAttribute = advplaceRelease.getAdvAttribute();// 广告位
							if (!advplaceRelease.getAdvplaceState().equals(0)) {// 不是发布中
							} else {
								boo = false;// 则不能修改状态,也就是不能再次出售
								break;
							}
						}
						if (!boo) {
							break;
						}
					}
				}
				if (boo) {// 可以修改状态
					for (int i = 0; i < biddingIds.length; i++) {
						AdvBidding advBiddingOld = advBiddingService
								.findByBiddingId(biddingIds[i]);// 获取到竞价信息
						List<AdvplaceRelease> advplaceReleaseList = advBiddingOld
								.getAdvplaceReleaseList();// 广告位发布列表
						if (advplaceReleaseList != null && advplaceReleaseList.size() > 0) {// 判断是否可以设置为竞拍成功
							for (AdvplaceRelease advplaceRelease : advplaceReleaseList) {
								advplaceReleaseService.modifyAdvplaReSta(advplaceRelease
										.getAdvplaceId().toString());// 修改该广告位为已出售状态
							}
						}
					}
					map.put("ids", ids);
					map.put("biddingStatus", biddingStatus);
					advBiddingService.modifyAdvbiddingStatus(map);
				} else {
					message = new Message(9, "选择的项中有的网页、海报广告位已被出售或者还在发布中!");
				}
			} else {
				map.put("ids", ids);
				map.put("biddingStatus", biddingStatus);
				advBiddingService.modifyAdvbiddingStatus(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统出错，请联系管理员");
		}
		this.htmlWrite(response, message);
		return null;
	}

	/**
	 * 修改竞价文件初始化
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_modify_advbiddingfile")
	public ModelAndView init_modify_advbiddingfile(HttpServletRequest request,
			HttpServletResponse resp) {
		String biddingId = request.getParameter("biddingId");// 广告竞价id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvBidding advbidding = advBiddingService.findByBiddingId(biddingId);
		model.put("advBidding", advbidding);
		return new ModelAndView("advbidding/init_modify_advbiddingfile", model);
	}

	/**
	 * 上传广告文件
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("upload_advbidding_file")
	public ModelAndView upload_advbidding_file(HttpServletRequest req,
			HttpServletResponse resp) {
		Message message = new Message(1, "文件修改成功!");
		String uploadPath = getServletContext().getRealPath("/upload/advbidding");
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
			// upload.setFileSizeMax(fileSizeMax);
			Iterator items;
			String fileName = null;
			String biddingId = null;
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
						if (!Utils.Str.isEmpty(name)) {
							fileName = name;
							// 上传文件以后的存储路径
							String filePath = uploadPath + File.separatorChar + fileName;
							File uploaderFile = new File(filePath);
							item.write(uploaderFile);
						}
					} else {
						if (item.getFieldName().equals("biddingId")) {
							biddingId = item.getString();
						}
					}
				}
				if (biddingId != null && fileName != null) {// 保存到数据库
					AdvBidding advBidding = new AdvBidding();
					advBidding.setBiddingId(Integer.parseInt(biddingId));// 主键id
					advBidding.setFileName(fileName);// 文件名
					advBiddingService.modifyFile(advBidding);
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统出错，请联系管理员!");
			}
		}
		// Utils.IO.writeText(Utils.Json.toJson(message), resp);
		this.htmlWrite(resp, message);
		return null;
	}

}
