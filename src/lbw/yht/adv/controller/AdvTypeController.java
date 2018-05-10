package lbw.yht.adv.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.param.AdvTypeParam;
import lbw.yht.adv.service.AdvTypeService;
import lbw.yht.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("advtype")
public class AdvTypeController extends BaseController {

	@Autowired
	private AdvTypeService advTypeService;// 类型

	/**
	 * 初始化广告位记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_adv_type")
	public ModelAndView init_adv_type(HttpServletRequest req,
			HttpServletResponse resp) {
		
		return new ModelAndView("adv/init_adv_type");
	}

	/**
	 * 条件查询广告位信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query_advtype_list")
	public ModelAndView query_advtype_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AdvTypeParam advTypeParam = new AdvTypeParam(
					this.initPage(request));// 分页条件
			
			String typeName = request.getParameter("typeName");//广告类型名称
			
			if (!Utils.Str.isEmpty(typeName)) {
				advTypeParam.setTypeName(typeName);// 设置类型编码
			}
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", advTypeService.queryCount(advTypeParam));
			model.put("rows", advTypeService.queryAllpageList(advTypeParam));
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
	@RequestMapping("init_add_advtype")
	public ModelAndView init_add_advtype(HttpServletRequest req,
			HttpServletResponse resp) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("adv/init_add_advtype", model);
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
		final String parentTypeCode = request.getParameter("parentTypeCode");// 广告位父类型
		final String typeName = request.getParameter("typeName");// 广告类型名称
		final String typeState = request.getParameter("typeState");// 状态
		final String typeContent = request.getParameter("typeContent");// 说明

		// 验证器
		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(typeName)) {
					return new Message(10, "请输入广告类型名称!", "typeName");
				} else if (typeName.length() > 32) {
					return new Message(10, "广告类型名称最大长度为32!", "typeName");
				}
			
				if (Utils.Str.isEmpty(typeContent)) {
					return new Message(10, "请输入广告位说明!", "typeContent");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvType advType = new AdvType();
				//获取广告类型编号
				String bytypeCode=advTypeService.findOneByTypeCode(parentTypeCode);
				String bytypeCode1=bytypeCode.substring(bytypeCode.length()-2);
				String typeCode=Utils.Str.Plus(Integer.valueOf(bytypeCode1));
				
				advType.setTypeCode(parentTypeCode+typeCode);
				advType.setParentTypeCode(parentTypeCode);//父类编号
				advType.setTypeName(typeName);//广告类型名称
				advType.setTypeState(typeState);// 状态
				advType.setTypeContent(typeContent);// 说明
				advType.setTypeDate(new Date());
				advTypeService.save(advType);
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
	@RequestMapping("init_modify_advtype")
	public ModelAndView init_modify_advtype(HttpServletRequest request,
			HttpServletResponse resp) {
		
		String typeCode = request.getParameter("typeCode");// 广告类型主键id
		Map<String, Object> model = new HashMap<String, Object>();
		AdvType advType = advTypeService.findOneByTypeCodeID(typeCode);
		model.put("advType", advType);
		return new ModelAndView("adv/init_modify_advtype", model);
	}

	/**
	 * 修改广告位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("modify_advtype")
	public ModelAndView modify_advtype(HttpServletRequest request,
			HttpServletResponse response) {
		final String typeCode = request.getParameter("typeCode");// 广告类型编号
		final String parentTypeCode = request.getParameter("parentTypeCode");// 父类编码
		final String typeName = request.getParameter("typeName");// 广告类型名称
		final String typeContent = request.getParameter("typeContent");//备注
		final String typeState = request.getParameter("typeState");// 状态
	

		// 验证器
		class Validator {
			public Message validate() {
			
		
				if (Utils.Str.isEmpty(typeName)) {
					return new Message(10, "请输入广告位尺寸!", "typeName");
				} else if (typeName.length() > 32) {
					return new Message(10, "广告类型名称字段最大长度为32!", "typeName");
				}
		
				if (Utils.Str.isEmpty(typeContent)) {
					return new Message(10, "请输入广告类备注!", "typeContent");
				}
				return new Message(1, null);
			}
		}
		Validator validator = new Validator();
		Message message = validator.validate();
		if (message.getCode() == 1) {
			try {
				AdvType advType = new AdvType();
				
				advType.setTypeCode(typeCode);
				advType.setParentTypeCode(parentTypeCode);
				advType.setTypeName(typeName);
				advType.setTypeDate(new Date());
				advType.setTypeState(typeState);
				advType.setTypeContent(typeContent);
				
				advTypeService.modify(advType);
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
		final String typeCode = request.getParameter("typeCode");// 广告位id
		final String typeState = request.getParameter("typeState");// 广告位id
		Message message = null;
		try {
			AdvType advType = new AdvType();
			
			advType.setTypeCode(typeCode);
			advType.setTypeState(typeState);
			advTypeService.updateAdvStatus(advType);
			
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
	@RequestMapping("remove_advtype")
	public ModelAndView remove_advtype(HttpServletRequest request,
			HttpServletResponse response) {
		final String advTypes = request.getParameter("advTypes");// 广告位ids
		Message message = null;
		try {
			advTypeService.delete(advTypes);
			message = new Message(1, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "删除失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}
	
}
