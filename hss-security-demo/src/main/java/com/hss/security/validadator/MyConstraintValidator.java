package com.hss.security.validadator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hss.security.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init(初始化)");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("Tom");
		System.out.println(value);
		//return false;
		return false; //返回true表示验证成功，false表示验证失败
	}

	
}
