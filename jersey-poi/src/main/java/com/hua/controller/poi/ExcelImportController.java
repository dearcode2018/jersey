/**
  * @filename ExcelImportController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller.poi;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.dayatang.excel.ExcelHandler;
import org.dayatang.excel.ExcelRange;
import org.dayatang.excel.ExcelRangeData;
import org.dayatang.excel.Version;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hua.bean.ResultBean;
import com.hua.entity.User;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

 /**
 * @type ExcelImportController
 * @description 
 * @author qye.zheng
 */
@Component
// 当前 jersey 不支持其他控制器使用相同的前缀
@Path("/import")
public class ExcelImportController
{
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @param uploadFile
	 * @author qye.zheng
	 */
	@Path("/do")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)  // 消费注解必须是这个类型
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void importExcel(FormDataMultiPart form, @Context UriInfo uri, @Context HttpServletRequest request,
			@Context final HttpServletResponse response)
	{
		FormDataBodyPart part = form.getField("uploadFile");  // 获取 BodyPart
	    String filename = part.getContentDisposition().getFileName(); // 获取原始文件名
	    System.out.println("filename = " + filename);
		User user = null;
		InputStream inputStream = null;
		ResultBean resultBean = new ResultBean();
		try
		{
			inputStream = part.getValueAs(InputStream.class);
			if (null == inputStream)
			{
				resultBean.setSuccess(false);
				resultBean.setMessage("请选择要导入的excel文件");
				toJSONHtmlOut(response, resultBean);
				return;
			}
			
			//System.out.println(filename);
			Version version = Version.of(filename);
			
			ExcelHandler excelHandler = new ExcelHandler(inputStream, version);
			
			ExcelRange excelRange = ExcelRange.sheetIndex(0);
			// 列的范围
			excelRange.columnRange(0, 4);
			// 从1行开始读
			excelRange.rowFrom(1);
			
			ExcelRangeData excelRangeData = excelHandler.readRange(excelRange);
			
			// 读取出数据
			 List<Object[]> data = excelRangeData.getData();
			
			Object[] objs = null;
			// int columnSize = data.get(0).length;
			for (int i = 0; i < data.size(); i++)
			{
				user = new User();
				objs = data.get(i);
				int j = 1;
				//user.setOid((Long) objs[j++]);
				user.setUsername(objs[j++].toString());
				user.setPassword(objs[j++].toString());
				//user.setLastLoginTime(ExcelUtils.getDate(objs[j++], version, true));
				System.out.println(user.getUsername());
				System.out.println(user.getLastLoginTime());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行数据回写至页面缓存 synchronized保证线程安全 Examples:(列举一些调用的例子)
	 * 
	 * @param response
	 * @param str
	 *            (设定参数)
	 */
	private synchronized void toJSONHtmlOut(HttpServletResponse response, ResultBean resultBean)
	{
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-no-cache");
		response.setHeader("expires", "0");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(resultBean);
			out.print(json);
			out.flush();
		} catch (IOException e)
		{
		} finally
		{
			if (out != null)
			{
				out.close();
			}
		}
	}
	
}
