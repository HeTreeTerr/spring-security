/**
 * 
 */
package com.hss.security.web.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lenovo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")//指定要访问的方式和路径
				.param("username", "jojo")//指定路径参数
				.param("age", "18")
				.param("ageto", "60")
				.param("xxx", "yyy")
				//.param("size", "15")
				//.param("page", "3")
				//.param("sort", "age,desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8))//指定返回的数据格式Json_Utf-8
				.andExpect(MockMvcResultMatchers.status().isOk())//指定返回状态200
//				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));//指定json的数据内容
				.andReturn().getResponse().getContentAsString()//String格式输出响应结果
				;
		System.out.println(result);
	}
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString()
				;
		System.out.println(result);
	}
	@Test
	public void whenGetInfofail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());//预测错误码4xx
		
	}
	@Test
	public void whenCreateSuccess() throws Exception {
		Date date = new Date();
		//输出时间戳
		System.out.println(date.getTime());
		String content = "{\"username\":\"ATM\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString()
				;
		System.out.println(result);
	}
	@Test
	public void whenUpdateSuccess() throws Exception {
		Date date = new Date(//或取在当前时间上加一年的时间
				LocalDateTime.now().plusYears(1)
				.atZone(ZoneId.systemDefault())
				.toInstant().toEpochMilli());
		//输出时间戳
		System.out.println(date.getTime());
		String content = "{\"id\":\"1\",\"username\":\"ATM\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString()
				;
		System.out.println(result);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()
				);
	}
	
	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
}
