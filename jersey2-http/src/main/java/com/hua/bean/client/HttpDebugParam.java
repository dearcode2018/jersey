/**
 * 描述: 
 * HttpDebugParam.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.hua.constant.Constant;
import com.hua.constant.HttpConstant;
import com.hua.util.ProjectUtil;

/**
 * 描述: http调试参数
 * 一般一个项目创建一个继承该类的参数
 * @author  qye.zheng
 * HttpDebugParam
 */
public abstract class HttpDebugParam
{
	
	/* 协议类型 默认是 http */
	private String protocol = "http";
	
	/* 主机域名或IP地址 */
	private String host = "127.0.0.1";
	
	/* 端口号 */
	private String port = "8080";
	
	/* 应用上下文 */
	private String contextPath;
	
	/* 接口/服务后缀路径 */
	private String suffixPath = "/api/login.do";
	
	/* 请求方法 */
	private String method = HttpConstant.REQUEST_METHOD_GET;
	
	/* 请求头 */
	private Map<String, String> headers = new HashMap<String, String>();
	
	/* 查询参数 key=value&key=value... */
	private Map<String, String> queryParams = new HashMap<String, String>();
	
	/* JSON 或 XML / HTML / XHTML ... */
	private String type;
	
	private Object data;
	
	/* 文件上传使用的name */
	private String name = "uploadFile";
	
	/* 文件上传_本地路径 */
	private String uploadPath;
	
	/* 文件传，放置在upload目录下的文件名 */
	private String filename;
	
	/* 默认是 /doc/download */
	private String downloadPath = ProjectUtil.getAbsolutePath("/doc/download", true);
	
	/* 请求的url */
	private String url;

	/**
	 * @return the protocol
	 */
	public final String getProtocol()
	{
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public final void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * @return the host
	 */
	public final String getHost()
	{
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public final void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public final String getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public final void setPort(String port)
	{
		this.port = port;
	}

	/**
	 * @return the contextPath
	 */
	public abstract String getContextPath();

	/**
	 * @param contextPath the contextPath to set
	 */
	public final void setContextPath(String contextPath)
	{
		this.contextPath = contextPath;
	}

	/**
	 * @return the suffixPath
	 */
	public final String getSuffixPath()
	{
		return suffixPath;
	}

	/**
	 * @param suffixPath the suffixPath to set
	 */
	public final void setSuffixPath(String suffixPath)
	{
		this.suffixPath = suffixPath;
	}

	/**
	 * @return the method
	 */
	public final String getMethod()
	{
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public final void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * @return the headers
	 */
	public final Map<String, String> getHeaders()
	{
		return headers;
	}

	/**
	 * 
	 * @description 
	 * @param name
	 * @param value
	 * @author qye.zheng
	 */
	public final void addHeader(final String name, final String value)
	{
		headers.put(name, value);
	}

	/**
	 * @return the queryParams
	 */
	public final Map<String, String> getQueryParams()
	{
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public final void addQueryParams(String name, String value)
	{
		queryParams.put(name, value);
	}

	/**
	 * @return the data
	 */
	public final Object getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public final void setData(Object data)
	{
		this.data = data;
	}

	/**
	 * @return the uploadPath
	 */
	public final String getUploadPath()
	{
		return uploadPath;
	}

	/**
	 * @param uploadPath the uploadPath to set
	 */
	public final void setUploadPath(String uploadPath)
	{
		this.uploadPath = uploadPath;
	}

	/**
	 * @return the downloadPath
	 */
	public final String getDownloadPath()
	{
		return downloadPath;
	}

	/**
	 * @param downloadPath the downloadPath to set
	 */
	public final void setDownloadPath(String downloadPath)
	{
		this.downloadPath = downloadPath;
	}

	/**
	 * @return the filename
	 */
	public final String getFilename()
	{
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public final void setFilename(String filename)
	{
		this.filename = filename;
	}

	/**
	 * @return the url
	 */
	public final String getUrl()
	{
		// http://127.0.0.1:8080/appName/suffixPath?a=1&b=2
		url = protocol + Constant.COLON + Constant.SLASH +  Constant.SLASH
				+ host + Constant.COLON + port + getContextPath() + suffixPath;
		
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public final void setUrl(String url)
	{
		this.url = url;
	}
	
	/**
	 * 描述: 
	 * @author qye.zhenge
	 * 
	 * @return
	 */
	@Override
	public String toString()
	{
		return new ReflectionToStringBuilder(this).toString();
	}

	/**
	 * @return the type
	 */
	public final String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name)
	{
		this.name = name;
	}

}
