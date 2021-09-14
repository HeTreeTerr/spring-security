package com.hss.security.service.impl;

import org.springframework.stereotype.Service;

import com.hss.security.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("Service开始执行");
		System.out.println("greeting");
		return "hello:"+name;
	}

}
