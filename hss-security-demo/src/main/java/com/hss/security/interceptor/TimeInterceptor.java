/**
 * 
 */
package com.hss.security.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lenovo
 *	拦截器会拦截所有控制器
 *	不光拦截自定义的请求，同时拦截spring定义的控制器
 *	只能拿到当前对象或对象声明，无法获取该方法的传值
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 * 控制器（controller）方法执行之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle!!!");
		//类名
		System.out.println( ((HandlerMethod)handler).getBean().getClass().getName() );
		//方法名
		System.out.println( ((HandlerMethod)handler).getMethod().getName() );
		request.setAttribute("startTime", new Date().getTime());
		//return false; //控制禁止进入下面的方法
		return true;	//控制进入下面的方法
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 * 控制器（controller）方法调用之后
	 * 如果控制器中抛出异常，此方法不执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle!!!");
		Long start = (Long)request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 * 无论控制器（controller）是否正常完成
	 * 都执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion!!!");
		Long start = (Long)request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
		System.out.println("ex is:"+ex);
	}

}
