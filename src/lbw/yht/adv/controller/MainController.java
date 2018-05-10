package lbw.yht.adv.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主界面控制器
 * 
 * @author cxj
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("main")
public class MainController {
	/**
	 * 主界面
	 * 
	 * @return
	 */
	@RequestMapping("init_main")
	public ModelAndView initMain(HttpServletRequest req, HttpServletResponse rep) {

		// List<Menu> list=new Gson().fromJson(
		// String.valueOf(MockSessionUtil.get(req, rep, MemKey.MENU)),
		// new TypeToken<Collection<Menu>>() {
		// }.getType());
		// List<Menu> userModuleList =new ArrayList<Menu>();
		// for (Menu menu : list) {
		// if(menu.getProject_code().equals("p04"))
		// userModuleList.add(menu);
		// }
		//
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("userModuleList", userModuleList);
		// model.put("emp", new Gson().fromJson(
		// MockSessionUtil.get(req, rep, MemKey.USER).toString(),
		// Emp.class));
		// model.put("part", new Gson().fromJson(
		// MockSessionUtil.get(req, rep, MemKey.ROLE).toString(),
		// Part.class));
		// return new ModelAndView("index", model);
		return new ModelAndView("index");
	}
}
