/**
  * @filename JerseyDebugClient.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.bean.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.hua.constant.HttpConstant;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;
import com.hua.util.StringUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;


 /**
 * @type JerseyDebugClient
 * @description jersey 调试客户端
 * @author qye.zheng
 */
public class JerseyDebugClient
{
	private HttpDebugParam param;
	
	private ClientResponse response;
	
	private  Client client;
	
	private WebResource webResource;
	
	private String result;
	
	private Builder builder;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public JerseyDebugClient(final HttpDebugParam param)
	{
		this.param = param;
	}
	
	/**
	 * 
	 * @description 
	 * @author qye.zheng
	 */
	private void init()
	{
		client = Client.create();
	    webResource = client.resource(param.getUrl());
	    Set<Map.Entry<String, String>> queryEntry = param.getQueryParams().entrySet();
	    Set<Map.Entry<String, String>> headerEntry = param.getHeaders().entrySet();
	    for (Map.Entry<String, String> e : queryEntry)
	    {
	    	webResource = webResource.queryParam(e.getKey(), e.getValue());
	    }
	    for (Map.Entry<String, String> e : headerEntry)
	    {
	    	if (null == builder)
	    	{
	    		builder = webResource.header(e.getKey(), e.getValue());
	    	} else
	    	{
	    		builder = builder.header(e.getKey(), e.getValue());
	    	}
	    }
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public String start()
	{
		init();
		switch (param.getMethod())
		{
			case HttpConstant.REQUEST_METHOD_GET:
				return get();
				
			case HttpConstant.REQUEST_METHOD_PUT:
				return put();
				
			case HttpConstant.REQUEST_METHOD_POST:
				return post();
				
			case HttpConstant.REQUEST_METHOD_DELETE:
				return delete();
				
			case HttpConstant.REQUEST_METHOD_OPTIONS:
				return options();
				
			default:
				return get();		
		}
	}
	
	/**
	 * 
	 * @description 文件上传
	 * @return
	 * @author qye.zheng
	 */
	public String upload()
	{
		init();
		OutputStream outputStream = null;
		try
		{
			File file = null;
			if (!StringUtil.isEmpty(param.getFilename()))
			{
				file = new File(ProjectUtil
						.getAbsolutePath("/doc/upload/" + param.getFilename(), true));
			} else
			{
				file = new File(param.getUploadPath());
			}
			FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(param.getName(), 
					file, MediaType.APPLICATION_OCTET_STREAM_TYPE);
			FormDataMultiPart multiPart = new FormDataMultiPart();
			multiPart.bodyPart(fileDataBodyPart);
			
			response = builder.type(MediaType.MULTIPART_FORM_DATA)
					.post(ClientResponse.class, multiPart);
			result = response.getEntity(String.class);
		} finally 
		{
			IOUtil.close(outputStream);
		}
	    
	    return result;
	}
	
	/**
	 * 
	 * @description 文件下载
	 * @author qye.zheng
	 */
	public void download()
	{
		init();
	    response = builder.get(ClientResponse.class);
	    final InputStream inputStream = response.getEntity(InputStream.class);
	    // 从中获取文件名 [attachment;filename=pom-full.xml]
	    String contentDisposition = response.getHeaders().get("Content-Disposition").get(0);
	   // System.out.println("contentDisposition = " + contentDisposition);
	    String keyWord = "filename=";
	    String filename = contentDisposition.substring(contentDisposition.indexOf(keyWord) + keyWord.length());
	   // System.out.println("filename = " + filename);
	    try
		{
			final OutputStream outputStream = new FileOutputStream(param.getDownloadPath() + "/" + filename);
			IOUtil.transport(inputStream, outputStream);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	private String get()
	{
	    response = builder.get(ClientResponse.class);
	    result = response.getEntity(String.class);
	    
	    return result;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	private String put()
	{
	    response = builder.type(MediaType.APPLICATION_JSON)
	    		.put(ClientResponse.class, param.getData());
	    result = response.getEntity(String.class);
	    
	    return result;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	private String post()
	{
	    response = builder.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, param.getData());
	    result = response.getEntity(String.class);
	    
	    return result;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	private String delete()
	{
	    response = builder.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class, param.getData());
	    result = response.getEntity(String.class);
	    
	    return result;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	private String options()
	{
	    response = builder.options(ClientResponse.class);
	    result = response.getEntity(String.class);
	    
	    return result;
	}
	
}
