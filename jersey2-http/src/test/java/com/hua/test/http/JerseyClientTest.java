/**
 * 描述: 
 * JerseyClientTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.http;

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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.client.ResponseData;
import com.hua.client.Jersey2Client;
import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JerseyClientTest
 */
public final class JerseyClientTest extends BaseTest {


	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPost() {
		try {
			String uri = "http://localhost:8080/spring-mvc-param/PersonController/postInBody";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			//queryParams.put("name", "zhangsan");
			//queryParams.put("password", "123456");
			
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			String data = "{\"name\":\"admin\", \"password\":\"123456\", \"password2\":\"123456\", \"queryBean\":{\"address\":\"广州天河区\"}, \"dtBetween\":{\"startDateTime\":\"2013-04-20\", \"endDateTime\":\"2017-02-16 20:25:08\"}}";
			ResponseData responseData = client.post(data);
			String result = responseData.getData();
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
			String uri = "http://localhost:8080/spring-mvc-param/PersonController/postInBody";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("id", "123");
			//queryParams.put("password", "123456");
			
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			String data = "{\"name\":\"admin\", \"password\":\"123456\", \"password2\":\"123456\", \"queryBean\":{\"address\":\"广州天河区\"}, \"dtBetween\":{\"startDateTime\":\"2013-04-20\", \"endDateTime\":\"2017-02-16 20:25:08\"}}";
			ResponseData responseData = client.delete();
			String result = responseData.getData();
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
	public void testPut() {
		try {
			String uri = "http://localhost:8080/spring-mvc-param/PersonController/postInBody";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			//queryParams.put("name", "zhangsan");
			//queryParams.put("password", "123456");
			
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			String data = "{\"name\":\"admin\", \"password\":\"123456\", \"password2\":\"123456\", \"queryBean\":{\"address\":\"广州天河区\"}, \"dtBetween\":{\"startDateTime\":\"2013-04-20\", \"endDateTime\":\"2017-02-16 20:25:08\"}}";
			ResponseData responseData = client.put(data);
			String result = responseData.getData();
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
	public void testGet() {
		try {
			String uri = "http://localhost:8080/spring-mvc-param/PersonController/get";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("name", "zhangsan");
			queryParams.put("password", "123456");
			
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			
			ResponseData responseData = client.get();
			System.out.println("status = " + responseData.getStatus());
			System.out.println("reason = " + responseData.getReasonPhrase());
			String result = responseData.getData();
			
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
	public void testOption() {
		try {
			String uri = "http://localhost:8080/spring-mvc-param/PersonController/get";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("name", "zhangsan");
			queryParams.put("password", "123456");
			
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			String data = "{\"name\":\"admin\", \"password\":\"123456\", \"password2\":\"123456\", \"queryBean\":{\"address\":\"广州天河区\"}, \"dtBetween\":{\"startDateTime\":\"2013-04-20\", \"endDateTime\":\"2017-02-16 20:25:08\"}}";
			ResponseData responseData = client.options();
			String result = responseData.getData();
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testOption =====> ", e);
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
			String uri = "http://localhost:8080/spring-file/api/file/upload/v1a";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			//headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			//queryParams.put("name", "zhangsan");
			//queryParams.put("password", "123456");
			String filePath = ClassPathUtil.getClassSubpath("/conf/properties/copy.properties");
			System.out.println("filePath = " + filePath);
			String name = "file";
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			ResponseData responseData = client.upload(name, filePath);
			String result = responseData.getData();
			
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
	public void testDownload() {
		try {
			String uri = "http://localhost:8080/spring-mvc-param/PersonController/get";
			// 头部信息
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("h1", "h1value aa");
			
			// 查询参数
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("name", "zhangsan");
			queryParams.put("password", "123456");
			
			Jersey2Client client = new Jersey2Client(uri, headers, queryParams);
			ResponseData responseData = client.get();
			String result = responseData.getData();
			
			System.out.println(result);
			
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
	public void testFile() {
		try {
			String path = "/E:/workspace/java/jersey2-http/target/classes/conf/properties/copy.properties";
			File file = new File(path);
			
			System.out.println(file.getName());
			System.out.println(file.length());
		} catch (Exception e) {
			log.error("testFile =====> ", e);
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
