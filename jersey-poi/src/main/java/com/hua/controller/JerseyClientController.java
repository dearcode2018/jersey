/**
  * @filename JerseyClientController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import com.hua.constant.Constant;
import com.hua.entity.User;
import com.hua.util.ClassPathUtil;
import com.hua.util.IOUtil;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.spi.resource.Singleton;

 /**
 * @type JerseyClientController
 * @description  
 * @author qye.zheng
 */
@Component
@Singleton
@Path("/JerseyClientController")
public class JerseyClientController
{
	
	/**
	 * @BeanParam (jersey 不支持), @PathParam, @QueryParam, @MatrixParam,
	 *  @HeaderParam, @CookieParam, @FormParam
	 * 
	 */
    
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@QueryParam("username") final String username, 
    		@QueryParam("password") final String password) {
			System.out.println("username = " + username + ", password = " + password);
		
        return "getRequest";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@PUT
	@Path("/put")
	@Produces(MediaType.TEXT_PLAIN)
    public String put(@Context  HttpServletRequest request, 
    		@Context HttpServletResponse response, final User user) {
			System.out.println("username = " + user.getUsername()
					+ ", password = " + user.getPassword());
		
        return "putRequest";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@POST
	@Path("/post")
	@Produces(MediaType.TEXT_PLAIN)
    public String post(@Context  HttpServletRequest request, 
    		@Context HttpServletResponse response, 
    		final User user) {
		System.out.println("username = " + user.getUsername()
				+ ", password = " + user.getPassword());
		
        return "postRequest";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
    public String delete(@Context  HttpServletRequest request, 
    		@Context HttpServletResponse response) {
		
        return "deleteRequest";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@OPTIONS
	@Path("/options")
	@Produces(MediaType.TEXT_PLAIN)
    public String options(@Context  HttpServletRequest request, 
    		@Context HttpServletResponse response, 
    		@QueryParam("username") final String username, 
    		@QueryParam("password") final String password) {
			System.out.println("username = " + username + ", password = " + password);
		
        return "getRequest";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)  // 消费注解必须是这个类型
	@Produces(MediaType.TEXT_PLAIN)
    public String upload(FormDataMultiPart form, @Context UriInfo uri, @Context HttpServletRequest reques) {
		// 获取 BodyPart
		FormDataBodyPart part = form.getField("uploadFile");
		// 获取原始文件名
	    String filename = part.getContentDisposition().getFileName();
	    System.out.println("filename = " + filename);
	    InputStream inputStream = part.getValueAs(InputStream.class);
		try
		{
			OutputStream outputStream = new FileOutputStream(ClassPathUtil.getClassSubpath("/conf/classpath/") + filename);
			IOUtil.transport(inputStream, outputStream);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
        return "uploadRequest";
    }
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
    public  byte[] download(@Context  HttpServletRequest request, 
    		@Context HttpServletResponse response, 
    		@QueryParam("username") final String username, 
    		@QueryParam("password") final String password) {
			System.out.println("username = " + username + ", password = " + password);
		
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
