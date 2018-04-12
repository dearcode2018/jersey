/**
  * @filename UploadController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller.poi;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

 /**
 * @type UploadController
 * @description 
 * @author qye.zheng
 */
@Component
@Path("/api")
public class UploadController
{

	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)  // 消费注解必须是这个类型
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void upload(FormDataMultiPart form, @Context UriInfo uri, @Context HttpServletRequest request) {
		
		FormDataBodyPart part = form.getField("uploadFile");  // 获取 BodyPart
	    String filename = part.getContentDisposition().getFileName(); // 获取原始文件名
	    System.out.println("filename = " + filename);
	    // InputStream in = part.getValueAs(InputStream.class) // 转为 InputStream 之后你读吧
	}
	
}
