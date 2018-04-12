/**
 * 描述: 
 * JerseyClientDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.client.driver;


/**
 * 描述:  - 驱动器
 * @author  qye.zheng
 * JerseyClientDriver
 */
public class JerseyClientDriver
{
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private JerseyClientDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean template()
	{
		boolean flag = false;
		try
		{
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
