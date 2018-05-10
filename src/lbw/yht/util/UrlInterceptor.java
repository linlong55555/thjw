package lbw.yht.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.domain.Constant;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.lbw.yht.bean.CacheConfig;
import cn.lbw.yht.framework.utils.MemKey;
import cn.lbw.yht.framework.utils.MockSessionUtil;

public class UrlInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 预处理
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object handler) throws Exception {
		// Cookie[] cookies = req.getCookies();
		// 获取当前访问的路径
		String currentUrl = req.getServletPath();

		String filterUrls = Constant.NO_VALIDATION;
		boolean validationRequest = filterUrl(filterUrls, currentUrl);
		if (validationRequest)// 不需要验证的请求
			return true;

		// url等于登陆界面||验证码
		if (currentUrl.equals(CacheConfig.DOMAIN))
			return true;
		Object emp = MockSessionUtil.get(req, resp, MemKey.USER);

		if (emp == null) {
			String js = "<script type='text/javascript'> top.location.href='"
					+ CacheConfig.DOMAIN + "';</script>";
			resp.getWriter().write(js);
			return false;
		}
		return true;
	}

	/**
	 * 过滤请求
	 * 
	 * @param filterUrls
	 *          可以过滤的请求
	 * @param currentUrl
	 *          当前请求
	 * @return
	 */
	private boolean filterUrl(String filterUrls, String currentUrl) {
		String[] fiterUrlArray = filterUrls.split(",");
		for (String url : fiterUrlArray) {
			if (currentUrl.startsWith(url)) {
				return true;
			}
		}
		return false;
	}
}