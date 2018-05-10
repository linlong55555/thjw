package lbw.yht.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lbw.yht.adv.domain.Constant;

public class InitServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {

		// 解析属性文件
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream("/interceptorconfig.properties");
			prop.load(is);

			Constant.NO_VALIDATION = prop.getProperty("no_validation");
			Constant.ERROR_URL = prop.getProperty("error_url");
			Constant.LOGIN_URL = prop.getProperty("login_url");
			Constant.RESOURCE_URL = prop.getProperty("resource_url");

			ServletContext context = event.getServletContext();
			context.setAttribute("resource_url", Constant.RESOURCE_URL);

		} catch (IOException e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
