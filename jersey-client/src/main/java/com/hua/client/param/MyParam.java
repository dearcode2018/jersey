/**
  * @filename MyParam.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.client.param;

import com.hua.bean.client.HttpDebugParam;

 /**
 * @type MyParam
 * @description 
 * @author qye.zheng
 */
public class MyParam extends HttpDebugParam
{

	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public String getContextPath()
	{
		return "/jersey-poi/rest";
	}
	
}
