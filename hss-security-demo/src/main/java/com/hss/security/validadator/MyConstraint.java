package com.hss.security.validadator;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//自定义标签
@Target({ElementType.METHOD,ElementType.FIELD})//注解对象：类和字段
@Retention(RetentionPolicy.RUNTIME)//运行时的注解
@Constraint(validatedBy = MyConstraintValidator.class)//当前注解用哪个类去效验
public @interface MyConstraint {
	
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
