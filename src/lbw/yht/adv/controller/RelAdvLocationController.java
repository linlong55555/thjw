package lbw.yht.adv.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.domain.param.RelAdvLocationParam;
import lbw.yht.adv.domain.param.AdvTypeParam;
import lbw.yht.adv.service.RelAdvLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("relAdvLocation")
public class RelAdvLocationController extends BaseController {

	@Autowired
	private RelAdvLocationService relAdvLocationService;// 筛选条件

	/**
	 * 筛选条件记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_adv_relAdvLocation")
	public ModelAndView init_adv_relAdvLocation(HttpServletRequest req,
			HttpServletResponse resp) {
		
		return new ModelAndView("adv/init_adv_relAdvLocation");
	}

	/**
	 * 筛选条件信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query_relAdvLocation_list")
	public ModelAndView query_relAdvLocation_list(HttpServletRequest request,
			HttpServletResponse response) {
		RelAdvLocationParam relAdvLocationParam = new RelAdvLocationParam(this.initPage(request));// 分页条件
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", relAdvLocationService.queryCount(relAdvLocationParam));
		model.put("rows", relAdvLocationService.queryAllpageList(relAdvLocationParam));
		this.htmlWrite(response, model);
		return null;
	}
	
	
	
}
