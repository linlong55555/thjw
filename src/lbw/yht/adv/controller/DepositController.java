package lbw.yht.adv.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.domain.param.BiddingDepositParam;
import lbw.yht.adv.service.BiddingDepositService;
import lbw.yht.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("deposit")
public class DepositController extends BaseController {

	@Autowired
	private BiddingDepositService biddingDepositService;//

	/**
	 * 初始化广告押金
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("init_deposit")
	public ModelAndView init_deposit(HttpServletRequest req,
			HttpServletResponse resp) {
		return new ModelAndView("deposit/init_deposit");
	}

	/**
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_deposit_list")
	public ModelAndView query_deposit_list(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			BiddingDepositParam biddingDepositParam = new BiddingDepositParam(
					this.initPage(request));// 分页条件
			String memberName = request.getParameter("memberName");// 旺乐用户名称
			String isReturn = request.getParameter("isReturn");// 是否归还
			if (!Utils.Str.isEmpty(memberName)) {
				biddingDepositParam.setMemberName(memberName);// 旺乐用户名称
			}
			if (!Utils.Str.isEmpty(isReturn)) {
				biddingDepositParam.setIsReturn(Integer.parseInt(isReturn));// 是否归还
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", biddingDepositService.queryCount(biddingDepositParam));
			model.put("rows",
					biddingDepositService.queryAllpageList(biddingDepositParam));
			this.htmlWrite(response, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改为违约
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping("modify_deposit")
	public ModelAndView modify_deposit(HttpServletRequest request,
			HttpServletResponse response) {
		final String depositId = request.getParameter("depositId");// 押金id
		Message message = null;
		try {
			biddingDepositService.modifyDeposit(depositId);
			message = new Message(1, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "修改失败!");
		}
		this.htmlWrite(response, message);
		return null;
	}

}
