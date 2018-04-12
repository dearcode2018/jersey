/**
 * @filename ExcelExportController.java
 * @description  
 * @version 1.0
 * @author qye.zheng
 */
package com.hua.controller.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.dayatang.excel.ExcelCell;
import org.dayatang.excel.ExcelHandler;
import org.springframework.stereotype.Component;

import com.hua.constant.Constant;
import com.hua.entity.User;

/**
 * @type ExcelExportController
 * @description
 * @author qye.zheng
 */
@Component
@Path("/export")
public class ExcelExportController
{

	
	/**
	 * 
	 * @description
	 * @param request
	 * @param response
	 * @author qye.zheng
	 */
	@GET
	@Path("/do")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] exportExcel(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response)
	{
		System.out.println("ExcelExportController.exportExcel11()");
		String filename = "export-dayatang.xls";
		/*
		 * 考虑到用a标签进行下载，弹出提示文件名，为了防止乱码或无法显示中文文件名， 必须进行转码将UTF-8 转成 ISO-8859-1
		 */
		final String agent = request.getHeader("USER-AGENT");
		// final boolean isMSIE = (agent != null && agent.indexOf("MSIE") !=
		// -1);
		final boolean isFireFox = (null != agent && agent.contains("Firefox"));
		OutputStream outputStream = null;
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
			User user = null;
			// Version version = Version.of(filePath);

			ExcelHandler excelHandler = new ExcelHandler();
			List<Object[]> data = new ArrayList<Object[]>();

			Object[] headers = new String[]
			{ "编号", "用户名", "最后一次登录时间" };
			data.add(headers);

			user = new User();
			user.setId("2008");
			user.setUsername("张三aa");
			//user.setLastLoginTime(new Date());
			Object[] values = new Object[]
			{ user.getId(), user.getUsername(), user.getLastLoginTime() };
			data.add(values);

			user = new User();
			user.setId("202342348");
			user.setUsername("李四bb");
			//user.setLastLoginTime(new Date());
			values = new Object[]
			{ user.getId(), user.getUsername(), user.getLastLoginTime() };
			data.add(values);

			ExcelCell topLeftCell = ExcelCell.sheetIndex(0);

			// 默认就是 0 列 0 行
			// topLeftCell.column(0);
			// topLeftCell.row(0);
			excelHandler.writeRange(topLeftCell, data);

			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);
			response.setCharacterEncoding("UTF-8");
			// response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/ms-excel");
			
			outputStream = response.getOutputStream();
			
			File file = File.createTempFile("temp", ".temp");
			excelHandler.outputTo(file);
			
			InputStream input = new FileInputStream(file);
			byte[] d = new byte[input.available()];
			input.read(d);
			
			return d;
			//ByteArrayInputStream
			// 输出
			//excelHandler.outputTo(outputStream);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}


}
