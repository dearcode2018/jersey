/**
  * @filename DownloadController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.hua.constant.Constant;
import com.hua.util.ClassPathUtil;
import com.hua.util.IOUtil;

 /**
 * @type DownloadController
 * @description  
 * @author qye.zheng
 */
@Component
@Path("/download")
public class DownloadController
{

	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/do")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] exportExcel(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response)
	{
		InputStream inputStream = null;
		byte[] data = null;
		String filename = "pom-full.xml";
		/*
		 * 考虑到用a标签进行下载，弹出提示文件名，为了防止乱码或无法显示中文文件名， 必须进行转码将UTF-8 转成 ISO-8859-1
		 */
		final String agent = request.getHeader("USER-AGENT");
		// final boolean isMSIE = (agent != null && agent.indexOf("MSIE") !=
		// -1);
		final boolean isFireFox = (null != agent && agent.contains("Firefox"));
		try
		{
			if (isFireFox)
			{
				// FireFox 浏览器
				filename = new String(filename.getBytes(), Constant.CHART_SET_ISO_8859_1);
			} else
			{
				// 其他浏览器
				filename = URLEncoder.encode(filename, Constant.CHART_SET_UTF_8);
			}
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);
			response.setCharacterEncoding("UTF-8");
			// response.setContentType("text/html;charset=UTF-8");
			//response.setContentType("application/ms-excel");
			inputStream = new FileInputStream(ClassPathUtil.getClassSubpath("/conf/classpath/pom-full.xml"));
			
			data = new byte[inputStream.available()];
			inputStream.read(data);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally 
		{
			IOUtil.close(inputStream);
		}
		
		return data;
	}
}
