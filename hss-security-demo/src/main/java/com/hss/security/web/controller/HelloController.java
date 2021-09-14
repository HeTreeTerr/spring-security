package com.hss.security.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(value = "/hello")
	public String hello() {
		return "hello world";
	}
	@GetMapping(value = "/getUsername")
	public String username() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal();  
			  
		String username = userDetails.getUsername();
		return username;
	}
}
