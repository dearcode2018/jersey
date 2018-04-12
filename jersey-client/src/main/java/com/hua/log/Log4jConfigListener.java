/**
* Log4jConfigListener.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.log;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.hua.util.WebPathUtil;

/**
 * 描述: 
 * @author qye.zheng
 * Log4jConfigListener
 */
public final class Log4jConfigListener implements ServletContextListener {

	private String filePath;
	
	private static final String paramName = "log4jConfigLocation";
	
	/* apache commons log */
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * 描述: 
	 * @author qye.zheng
	 * @param arg0
	 */
	//@Override
	public void contextDestroyed(final ServletContextEvent event) {
		log.info("contextDestroyed =====> context destroy");
		
	}

	/**
	 * 描述: 
	 * @author qye.zheng
	 * @param arg0
	 */
	//@Override
	public void contextInitialized(final ServletContextEvent event) {
		final ServletContext context = event.getServletContext();
		// 获取web根路径
		final String webRootPath = WebPathUtil.getWebRootPath(context);
		// 初始化参数 - 获取路径参数
		filePath = webRootPath + context.getInitParameter(paramName);
		//  使用 log 配置文件，初始化log4j
		PropertyConfigurator.configure(filePath);
		log.info("contextInitialized =====> filePath = " + filePath );
		log.info("contextInitialized =====> 使用 log 配置文件，初始化log4j");
	}

}
