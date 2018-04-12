/**
  * @filename RestTest.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import com.hua.entity.User;
import com.hua.entity.UserLog;
//import com.sun.jersey.spi.resource.Singleton;

 /**
 * @type RestTest
 * @description  
 * @author qye.zheng
 */
//@Component
//@Singleton
// 方法可以覆盖此配置
//@Produces(MediaType.APPLICATION_JSON)
//@Path("/RestTest")
public class RestTestBak
{
	
	/**
	 * @BeanParam (jersey 不支持), @PathParam, @QueryParam, @MatrixParam,
	 *  @HeaderParam, @CookieParam, @FormParam
	 * 
	 */
	
	@PathParam("p")
	private String pathParam;
 
    @MatrixParam("m")
    @Encoded
    @DefaultValue("default")
    private String matrixParam;
 
    @HeaderParam("header")
    private String headerParam;
 
    private String queryParam;
	
	@Context 
	private HttpServletRequest request;
    
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@Path("/users/{username}")
    @GET
    // 返回文本字符串
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(
    		/* PathParam 使用了@Path部分的参数 */
    		@PathParam("username") String username) {
	 
		System.out.println("enter ...");
	 	
		System.out.println("TestRest.getUser()");

	 	System.out.println("username = " + username);
	 	
	 	System.out.println("ahhahhah");
	 	
        return username;
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	// 使用正则进行拦截验证
	@Path("/a/{username: [a-zA-Z 0-9]*}")
    @GET
    @Consumes({MediaType.TEXT_PLAIN})
    // 返回文本字符串
    @Produces(MediaType.TEXT_PLAIN)
    public String testRegular(
    		/* PathParam 使用了@Path部分的参数 */
    		@PathParam("username") String username) {
	 
		System.out.println("enter ...");
	 	
		System.out.println("TestRest.getUser()");

	 	System.out.println("username = " + username);
	 	
	 	System.out.println("ahhahhah");
	 	
        return username;
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/singleParam")
	@Consumes(MediaType.APPLICATION_JSON) 
	/*
	 * 多个输出类型 由客户端 accept 来决定输出的类型，
	 * 若accept出现多个，则使用第一个找到的或使用优先级高的
	 */
    @Produces({MediaType.APPLICATION_JSON, 
    	MediaType.APPLICATION_ATOM_XML})
    public String singleParam(@Context User user) {
	 	System.out.println("RestTest.singleParam()");
	 	
	 	System.out.println(user.getUsername());
	 	
        return "singleParam";
    }

	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/multipleParam")
    @Produces(MediaType.TEXT_PLAIN)
    public String multipleParam(User user, UserLog userLog) {
	 
	 	System.out.println("RestTest.multipleParam()");
	 	System.out.println(user.toString());
	 	System.out.println(userLog.toString());
	 	
        return "multipleParam";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/queryParam")
    @Produces(MediaType.TEXT_PLAIN)
    public String queryParam(@DefaultValue("-1") @QueryParam("step") int step,
    		@DefaultValue("false") @QueryParam("hasMin") boolean hasMin,
    		// 使用某个查询参数来构造一个对象(接收单个基本类型的参数的构造方法必须存在)
    		@DefaultValue("zhangsan") @QueryParam("username") User user) {

		System.out.println("step = " + step);
		System.out.println("hasMin = " + hasMin);
	 	
        return "multipleParam";
    }

	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/uriInfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String uriInfo(@Context UriInfo uriInfo) {
		// 多个值的map
		 MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		 
		 MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();
		 
        return "multipleParam";
    }

	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/uriInfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String headerParam(@Context HttpHeaders headers,
    		@Context HttpServletRequest request, 
    		@Context HttpServletResponse response) {
		System.out.println("request = " + request);
		// 多个值的map
		MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();
	   	Map<String, Cookie> pathParams = headers.getCookies();	
	   	
        return "multipleParam";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@GET
	@Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public String request(@Context HttpServletResponse response) {
		System.out.println("request = " + request);
		// 多个值的map
		
        return "multipleParam";
    }
	
	/**
	 * 
	 * @description 
	 * @param username
	 * @return
	 * @author qye.zheng
	 */
	@POST
	@Path("/uriInfo")
    @Produces(MediaType.TEXT_PLAIN)
	/*
	 * 可以直接使用MultivaluedMap<String, String>的方式接收参数而无需
	 * @Context
	 */
    public String multivalueMap(MultivaluedMap<String, String> formParams) {
		// 多个值的map
		
		
        return "multipleParam";
    }
	
}
