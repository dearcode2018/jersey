/**
* BaseLog.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.hua.util.ClassPathUtil;

/**
 * 描述: 
 * @author qye.zheng
 * BaseLog
 */
public class BaseLog {
	
	private static final String FILE_PATH = "/conf/properties/log4j.properties";
	
	/* apache commons log */
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	static {
		// 初始化 log4j 环境
		PropertyConfigurator.configure(ClassPathUtil.getClassSubpath(FILE_PATH));
	}
}
