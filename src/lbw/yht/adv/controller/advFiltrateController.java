package lbw.yht.adv.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.domain.param.AdvFiltrateParam;
import lbw.yht.adv.domain.param.AdvTypeParam;
import lbw.yht.adv.service.AdvFiltrateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("advFiltrate")
public class advFiltrateController extends BaseController {

	@Autowired
	private AdvFiltrateService advFiltrateService;// 筛选条件

	/**
	 * 筛选条件记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_adv_advFiltrate")
	public ModelAndView init_adv_advFiltrate(HttpServletRequest req,
			HttpServletResponse resp) {
		
		return new ModelAndView("adv/init_adv_advFiltrate");
	}

	/**
	 * 筛选条件信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query_advFiltrate_list")
	public ModelAndView query_advFiltrate_list(HttpServletRequest request,
			HttpServletResponse response) {
		AdvFiltrateParam advFiltrateParam = new AdvFiltrateParam(this.initPage(request));// 分页条件
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("rows", advFiltrateService.queryAllpageList(advFiltrateParam));
		this.htmlWrite(response, model);
		return null;
	}
	
	
	
}
