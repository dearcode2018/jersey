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

 /**
 * @type RestTest
 * @description  
 * @author qye.zheng
 */
@Component
//@Singleton
// 方法可以覆盖此配置
@Produces(MediaType.APPLICATION_JSON)
//@Path("/RestTest/{p}")
@Path("/RestTest")
public class RestTest
{
	
	/**
	 * @BeanParam (jersey 不支持), @PathParam, @QueryParam, @MatrixParam,
	 *  @HeaderParam, @CookieParam, @FormParam
	 * 
	 */

	//@PathParam("p")
	//private String pathParam;
	
	//@MatrixParam("m")
    //@Encoded
	//@DefaultValue("default")
    //private String matrixParam;
	 
    //@HeaderParam("header")
    //private String headerParam;
	
    private String queryParam;
	
   @Context
   private HttpServletRequest request;
    
	@Context
	private HttpServletResponse response;
    
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
    public String request(@Context  HttpServletRequest request, 
    		@Context HttpServletResponse response) {
		System.out.println("request = " + request);
		System.out.println("response = " + response);
		// 多个值的map
		
        return "multipleParam";
    }
	
}
