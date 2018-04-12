/**
 * 描述: 
 * JerseyClientTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.client;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.client.HttpDebugParam;
import com.hua.bean.client.JerseyDebugClient;
import com.hua.client.param.MyParam;
import com.hua.constant.HttpConstant;
import com.hua.entity.User;
import com.hua.test.BaseTest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JerseyClientTest
 */
public final class JerseyClientTest extends BaseTest {

	private ClientResponse response;
	
	private  Client client;
	
	private WebResource webResource;
	
	private String result;
	
	private Builder builder;
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			HttpDebugParam param = new MyParam();
			param.setSuffixPath("/JerseyClientController/get");
			param.addHeader("a", "1");
			param.addQueryParams("username", "张三");
			param.addQueryParams("password", "123456");
			param.setMethod(HttpConstant.REQUEST_METHOD_GET);
			JerseyDebugClient client = new JerseyDebugClient(param);
			String result = client.start();
			System.out.println(result);
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPut() {
		try {
			User data = new User();
			data.setUsername("张三");
			data.setPassword("123456");
			HttpDebugParam param = new MyParam();
			param.setSuffixPath("/JerseyClientController/put");
			param.addHeader("Content-Type", "application/json;charset=UTF-8");

			param.setMethod(HttpConstant.REQUEST_METHOD_PUT);
			param.setData(data);
			JerseyDebugClient client = new JerseyDebugClient(param);
			String result = client.start();
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testPut =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPost() {
		try {
			User data = new User();
			data.setUsername("张三");
			data.setPassword("123456");
			HttpDebugParam param = new MyParam();
			param.setSuffixPath("/JerseyClientController/post");
			param.addHeader("Content-Type", "application/json;charset=UTF-8");
			param.setMethod(HttpConstant.REQUEST_METHOD_POST);
			param.setData(data);
			JerseyDebugClient client = new JerseyDebugClient(param);
			String result = client.start();
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testPost =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			User data = new User();
			data.setUsername("张三");
			data.setPassword("123456");
			HttpDebugParam param = new MyParam();
			param.setSuffixPath("/JerseyClientController/delete");
			param.addHeader("Content-Type", "application/json;charset=UTF-8");
			param.setMethod(HttpConstant.REQUEST_METHOD_DELETE);
			param.setData(data);
			JerseyDebugClient client = new JerseyDebugClient(param);
			String result = client.start();
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testOptions() {
		try {
			User data = new User();
			HttpDebugParam param = new MyParam();
			param.setSuffixPath("/JerseyClientController/options");
			param.addQueryParams("username", "张三");
			param.addQueryParams("password", "123456");
			param.addHeader("a", "aa");
			param.setMethod(HttpConstant.REQUEST_METHOD_OPTIONS);
			JerseyDebugClient client = new JerseyDebugClient(param);
			String result = client.start();
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testOptions =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpload() {
		try {
			User data = new User();
			HttpDebugParam param = new MyParam();
			param.addHeader("a", "aa");
			param.setSuffixPath("/JerseyClientController/upload");
			param.setFilename("export-dayatang.xlsx");
			JerseyDebugClient client = new JerseyDebugClient(param);
			String result = client.upload();
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testUpload =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUploadA() {
		try {
			/**
			 * org.apache.http.client.HttpClient
			 * 
			 * 通过 HttpClientBuilder 来构建HttpClient 对象
			 * 
			 * DefaultHttpClient 等实现对象已经过时，不推荐使用
			 */
/*			httpClient = HttpClientBuilder.create().build();
			testUrl = "http://127.0.0.1:8080/jersey-poi/rest/JerseyClientController/upload";
			// http get 实例
			httpPost = new HttpPost(testUrl);
			
			///FileBody bin = new FileBody(new File(ProjectUtil
				//	.getAbsolutePath("/doc/upload/export-dayatang.xlsx", true)));  
			//StringBody filename = new StringBody("export-dayatang.xlsx", ContentType.TEXT_PLAIN);  
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addBinaryBody("uploadFile", new File(ProjectUtil
					.getAbsolutePath("/doc/upload/export-dayatang.xlsx", true)));
			
			HttpEntity entity = builder.build();
			
			httpPost.setEntity(entity);
			
			response = httpClient.execute(httpPost);
			
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				// 成功响应 200 ok
				input = response.getEntity().getContent();
				result = StringUtil.convertStreamToString(input);
				log.info("testPost =====> result = " + result );*/
				
		} catch (Exception e) {
			log.error("testUpload =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDownload() {
		try {
			HttpDebugParam param = new MyParam();
			param.addHeader("a", "aa");
			param.setSuffixPath("/JerseyClientController/download");
			JerseyDebugClient client = new JerseyDebugClient(param);
			client.download();
		} catch (Exception e) {
			log.error("testDownload =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testA() {
		try {
			User data = new User();
			data.setUsername("张三");
			data.setPassword("123456");
			String serverUri = "http://localhost:8080/jersey-poi/rest/JerseyClientController/post";
		     Client client = Client.create();
		     WebResource webResource =client.resource(serverUri);

		     //Get response from RESTful Server get(ClientResponse.class);
		     ClientResponse response = null;
		     response = webResource.header("Content-Type", "application/json;charset=UTF-8")
		                            .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, data);
		     System.out.println(response);
		     String jsonStr = response.getEntity(String.class);
			System.out.println(jsonStr);
		} catch (Exception e) {
			log.error("testA =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testA2() {
		try {
			User data = new User();
			data.setUsername("张三");
			data.setPassword("123456");
			String serverUri = "http://localhost:8080/jersey-poi/rest/JerseyClientController/put";
		     Client client = Client.create();
		     WebResource webResource =client.resource(serverUri);

		     //Get response from RESTful Server get(ClientResponse.class);
		     ClientResponse response = null;
		     response = webResource.header("Content-Type", "application/json;charset=UTF-8")
		                            .type(MediaType.APPLICATION_JSON).put(ClientResponse.class, data);
		     System.out.println(response);
		     String jsonStr = response.getEntity(String.class);
			System.out.println(jsonStr);
		} catch (Exception e) {
			log.error("testA =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testA3() {
		try {
			User data = new User();
			data.setUsername("张三");
			data.setPassword("123456");
			String serverUri = "http://localhost:8080/jersey-poi/rest/JerseyClientController/get";
		     Client client = Client.create();
		     WebResource webResource =client.resource(serverUri);

		     //Get response from RESTful Server get(ClientResponse.class);
		     ClientResponse response = null;
		     response = webResource.queryParam("username", data.getUsername())
		 		    .queryParam("password", data.getPassword()).header("Content-Type", "application/json;charset=UTF-8")
		            .type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		     System.out.println(response);
		     String jsonStr = response.getEntity(String.class);
			System.out.println(jsonStr);
		} catch (Exception e) {
			log.error("testA =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			HttpDebugParam param = new MyParam();
			param.setSuffixPath("/JerseyClientController/post");
			client = Client.create();
		    webResource = client.resource(param.getUrl());
		    System.out.println(param.getUrl());
		   // response = builder.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, param.getData());
			webResource.header("Content-Type", "application/json;charset=UTF-8").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, param.getData());
		    result = response.getEntity(String.class);
			
		    System.out.println(result);
		    
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
