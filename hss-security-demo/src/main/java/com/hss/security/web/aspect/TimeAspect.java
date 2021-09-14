package com.hss.security.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author lenovo
 * 切片，可以获取调用的具体方法和传参，但不能获取http请求的相关信息
 */
@Aspect
@Component
public class TimeAspect {
	
	@Around("execution(* com.hss.security.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start ");
		Object[] args = pjp.getArgs();
		for(Object arg : args) {
			System.out.println("arg is:"+arg);
		}
		
		long start = new Date().getTime();
		Object object = pjp.proceed();	//开放切面，调用底层方法
		long end = new Date().getTime();
		
		System.out.println("TimeAspect:"+(end-start));
		
		System.out.println("time aspect end ");
		return object;
	}
}
