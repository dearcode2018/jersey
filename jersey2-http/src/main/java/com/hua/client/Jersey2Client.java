/**
  * @filename Jersey2Client.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import com.hua.bean.client.ResponseData;
import com.hua.util.IOUtil;


 /**
 * @type Jersey2Client
 * @description jersey2 客户端 (Json 数据协议)
 * @author qye.zheng
 */
public class Jersey2Client
{
	private Response response;
	
	private  Client client;
	
	private WebTarget webTarget;
	
	private String result;
	
	private Builder builder;
	
	private String uri;
	
	/* 头部信息 */
	private Map<String, Object> headers = new HashMap<String, Object>();
	
	/* 查询参数 */
	private Map<String, Object> queryParams = new HashMap<String, Object>();
	
	/**
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	public Jersey2Client()
	{
		super();
	}

	/**
	 * @description 构造方法
	 * @param uri
	 * @param headers
	 * @author qianye.zheng
	 */
	public Jersey2Client(String uri, Map<String, Object> headers)
	{
		super();
		this.uri = uri;
		this.headers = headers;
	}

	/**
	 * 
	 * @description 构造方法
	 * @param headers
	 * @param queryParams
	 * @author qianye.zheng
	 */
	public Jersey2Client(final String uri, final Map<String, Object> headers, final Map<String, Object> queryParams)
	{
		this.uri = uri;
		this.headers = headers;
		this.queryParams = queryParams;
	}
	
	/**
	 * 
	 * @description 
	 * @author qye.zheng
	 */
	private void init()
	{
		client = ClientBuilder.newClient();
		webTarget = client.target(uri);
		Set<Map.Entry<String, Object>> queryEntry = null;
		Set<Map.Entry<String, Object>> headerEntry = null;
		if (null != headers)
		{
			headerEntry = headers.entrySet();
		}
		if (null != queryParams)
		{
			queryEntry = queryParams.entrySet();
		}
		// 构造请求
		builder = webTarget.request();
	    for (Map.Entry<String, Object> e : queryEntry)
	    {
	    	// 查询参数
	    	webTarget = webTarget.queryParam(e.getKey(), e.getValue());
	    }
	    // 设置请求头
	    for (Map.Entry<String, Object> e : headerEntry)
	    {
	    	builder.header(e.getKey(), e.getValue());
	    }
	    // 公共支持Json数据协议，文件下载另外设置
	    builder.accept(MediaType.APPLICATION_JSON);
	}
	
	/**
	 * 
	 * @description 创建
	 * @param data
	 * @return
	 * @author qianye.zheng
	 */
	public ResponseData post(final String data)
	{
		init();
		final Entity<?> entity = Entity.json(data);
		response = builder.post(entity);
		ResponseData responseData = null;
		if (null == response)
		{
			
			return responseData;
		}
		responseData = new ResponseData();
		result = response.readEntity(String.class);
		responseData.setData(result);
		responseData.setStatus(response.getStatus());
		responseData.setReasonPhrase(response.getStatusInfo().getReasonPhrase());
		
	    return responseData;
	}
	
	/**
	 * 
	 * @description 删除
	 * @return
	 * @author qye.zheng
	 */
	public ResponseData delete()
	{
		init();
		response = builder.delete();
		ResponseData responseData = null;
		if (null == response)
		{
			
			return responseData;
		}
		responseData = new ResponseData();
		result = response.readEntity(String.class);
		responseData.setData(result);
		responseData.setStatus(response.getStatus());
		responseData.setReasonPhrase(response.getStatusInfo().getReasonPhrase());
		
	    return responseData;		
	}
	
	/**
	 * 
	 * @description 修改
	 * @param data
	 * @return
	 * @author qianye.zheng
	 */
	public ResponseData put(final String data)
	{
		init();
		final Entity<?> entity = Entity.json(data);
		response = builder.put(entity);
		ResponseData responseData = null;
		if (null == response)
		{
			
			return responseData;
		}
		responseData = new ResponseData();
		result = response.readEntity(String.class);
		responseData.setData(result);
		responseData.setStatus(response.getStatus());
		responseData.setReasonPhrase(response.getStatusInfo().getReasonPhrase());
		
	    return responseData;
	}
	
	/**
	 * 
	 * @description 查询
	 * @return
	 * @author qye.zheng
	 */
	public ResponseData get()
	{
		init();
		response = builder.get();
		ResponseData responseData = null;
		if (null == response)
		{
			
			return responseData;
		}
		responseData = new ResponseData();
		result = response.readEntity(String.class);
		responseData.setData(result);
		responseData.setStatus(response.getStatus());
		responseData.setReasonPhrase(response.getStatusInfo().getReasonPhrase());
		
	    return responseData;	    
	}
	
	/**
	 * 
	 * @description 可选
	 * @return
	 * @author qye.zheng
	 */
	public ResponseData options()
	{
		init();
		response = builder.options();
		ResponseData responseData = null;
		if (null == response)
		{
			
			return responseData;
		}
		responseData = new ResponseData();
		result = response.readEntity(String.class);
		responseData.setData(result);
		responseData.setStatus(response.getStatus());
		responseData.setReasonPhrase(response.getStatusInfo().getReasonPhrase());
		
	    return responseData;
	}
	
	/**
	 * 
	 * @description 文件上传
	 * @param name
	 * @param filePath 文件路径
	 * @return
	 * @author qianye.zheng
	 */
	public ResponseData upload(final String name, final String filePath)
	{
		init();
		OutputStream outputStream = null;
		ResponseData responseData = null;
		try
		{
			File file = null;
			file = new File(filePath);
			final FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(name, 
					file, MediaType.APPLICATION_OCTET_STREAM_TYPE);
			final FormDataMultiPart multiPart = new FormDataMultiPart();
			multiPart.bodyPart(fileDataBodyPart);
			final Entity<FormDataMultiPart> entity = Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA);
			response = builder.post(entity);
			if (null == response)
			{
				
				return responseData;
			}
			
			responseData = new ResponseData();
			result = response.readEntity(String.class);
			responseData.setData(result);
			responseData.setStatus(response.getStatus());
			responseData.setReasonPhrase(response.getStatusInfo().getReasonPhrase());
		} finally 
		{
			IOUtil.close(outputStream);
		}
	    
	    return responseData;
	}
	
	/**
	 * 
	 * @description 文件下载
	 * @param downloadPath 下载文件存放路径(绝对路径 例如 /a/b)
	 * @author qianye.zheng
	 */
	public void download(final String downloadPath)
	{
		init();
	    //response = builder.get(ClientResponse.class);
	    final InputStream inputStream = response.readEntity(InputStream.class);
	    // 从中获取文件名 [attachment;filename=pom-full.xml]
	    //String contentDisposition = response.getHeaders().get("Content-Disposition").get(0);
	   // System.out.println("contentDisposition = " + contentDisposition);
	    String contentDisposition = null;
	    String keyWord = "filename=";
	    String filename = contentDisposition.substring(contentDisposition.indexOf(keyWord) + keyWord.length());
	   // System.out.println("filename = " + filename);
	    try
		{
			final OutputStream outputStream = new FileOutputStream(downloadPath + "/" + filename);
			IOUtil.transport(inputStream, outputStream);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the uri
	 */
	public final String getUri()
	{
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public final void setUri(String uri)
	{
		this.uri = uri;
	}

	/**
	 * @return the headers
	 */
	public final Map<String, Object> getHeaders()
	{
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public final void addHeader(final String name, final Object value)
	{
		headers.put(name, value);
	}

	/**
	 * @return the queryParams
	 */
	public final Map<String, Object> getQueryParams()
	{
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public final void addQueryParam(final String name, final Object value)
	{
		queryParams.put(name, value);
	}
	
}
