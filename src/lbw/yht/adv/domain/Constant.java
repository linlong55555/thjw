package lbw.yht.adv.domain;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

public class Constant {
	// 以该字符串开头的地址，不需要进行验证,多个用逗号分隔，例如'/login/,/user/'
	public static String NO_VALIDATION;
	// 出错时跳转的地址
	public static String ERROR_URL;
	// 在缓存中不存在用户数据时，跳转的地址
	public static String LOGIN_URL;
	// 静态资源地址读取路径，跳转的地址
	public static String RESOURCE_URL;

	public static ApplicationContext APPLICATION_CONTEXT;

	public static ServletContext SERVLET_CONTEXT;

}
