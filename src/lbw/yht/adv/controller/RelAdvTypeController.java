package lbw.yht.adv.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.domain.param.RelAdvTypeParam;
import lbw.yht.adv.service.RelAdvTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("relAdvType")
public class RelAdvTypeController extends BaseController {

	@Autowired
	private RelAdvTypeService relAdvTypeService;// 筛选条件

	/**
	 * 筛选条件记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_adv_relAdvType")
	public ModelAndView init_adv_relAdvType(HttpServletRequest req,
			HttpServletResponse resp) {
		
		return new ModelAndView("adv/init_adv_relAdvType");
	}

	/**
	 * 筛选条件信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query_relAdvType_list")
	public ModelAndView query_relAdvType_list(HttpServletRequest request,
			HttpServletResponse response) {
		RelAdvTypeParam relAdvTypeParam = new RelAdvTypeParam(this.initPage(request));// 分页条件
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", relAdvTypeService.queryCount(relAdvTypeParam));
		model.put("rows", relAdvTypeService.queryAllpageList(relAdvTypeParam));
		this.htmlWrite(response, model);
		return null;
	}
	
	
	
}
