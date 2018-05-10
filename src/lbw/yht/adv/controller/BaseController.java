package lbw.yht.adv.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.service.BaseService;
import lbw.yht.util.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public abstract class BaseController extends MultiActionController {
	@Autowired
	protected BaseService baseService;

	protected final Log LOG = LogFactory.getLog(getClass());

	/*
	 * // 统一异常处理
	 * 
	 * @ExceptionHandler public String exception(HttpServletRequest request,
	 * Exception e) { // 添加自己的异常处理逻辑，如日志记录　　　
	 * request.setAttribute("exceptionMessage", e.getMessage());
	 * 
	 * // 根据不同的异常类型进行不同处理 if (e instanceof SQLException) return "testerror";
	 * else return "error"; }
	 */

	protected void htmlWrite(HttpServletResponse response, Object json) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().print(
					Utils.Json.toJson(json, "yyyy-MM-dd HH:mm:ss"));
			// response.getWriter().print(Utils.Json.gsonToJson(json));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化分页
	 * 
	 * @param request
	 */
	public Pagination initPage(HttpServletRequest request) {
		Pagination pagination = new Pagination();
		pagination.setPage(Integer.parseInt(request.getParameter("page")));
		pagination.setRows(Integer.parseInt(request.getParameter("rows")));
		pagination.setSort(request.getParameter("sort"));
		pagination.setOrder(request.getParameter("order"));
		pagination.initPageIndex();
		return pagination;
	}

	@RequestMapping("comboboxLoad")
	public ModelAndView comboboxLoad(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		String tabname = request.getParameter("tabname");
		List list = baseService.comboboxLoad(id, text, tabname);
		this.htmlWrite(response, list);
		return null;
	}

}
