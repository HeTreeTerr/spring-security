/**
 * 
 */
package com.hss.security.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hss.security.dto.User;
import com.hss.security.dto.UserQueryCondition;
import com.hss.security.exception.UserNotExistException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author lenovo
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	//@RequestBody:@RequestBody的作用其实是将json格式的数据转为java对象。
	@PostMapping
	public User create(@Valid @RequestBody User user/*,BindingResult errors*/) {
		
		/*if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError)error;
				String message = fieldError.getField()+"-----"+error.getDefaultMessage();
				System.out.println(message);
				}
			);
		}*/
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	
	@PutMapping(value = "/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError)error;
				String message = fieldError.getField()+"-----"+error.getDefaultMessage();
				System.out.println(message);
				}
			);
		}
		System.out.println(user);
		return user;
	}
	
	@DeleteMapping(value = "/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
	/*@RequestMapping(value = "/user",method = RequestMethod.GET)
	public List<User> query(@RequestParam(name="username",required=false,defaultValue="tom") String username) {
		System.out.println("username="+username);
		List<User> users = new ArrayList<>();
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		return users;
	}*/
	
	//@RequestMapping(value = "/user",method = RequestMethod.GET)
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryCondition userQueryCondition,
			@PageableDefault(page=0,size=5,sort="age,desc") Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(userQueryCondition,ToStringStyle.MULTI_LINE_STYLE));
		
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	/*
	 * 使用正则表达式效验数据
	 * */
	//@RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
	@GetMapping(value = "/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam(value = "用户id") @PathVariable(name="id") String id) {
		System.out.println("进入getInfo服务");
		//throw new UserNotExistException(id);
		//throw new RuntimeException("Service error");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
}
