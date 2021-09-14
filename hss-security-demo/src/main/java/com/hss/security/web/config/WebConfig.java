/**
 * 
 */
package com.hss.security.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hss.security.filter.TimeFilter;
import com.hss.security.interceptor.TimeInterceptor;

/**
 * @author lenovo
 *	
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor timeInterceptor; 
	
	//针对异步处理进行拦截器配置
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		super.configureAsyncSupport(configurer);
		/*configurer.registerCallableInterceptors(interceptors);
		configurer.registerDeferredResultInterceptors(interceptors);*/
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		super.addInterceptors(registry);
		
		registry.addInterceptor(timeInterceptor)
		.addPathPatterns("/user/**","/hello")	//定义拦截哪些请求(默认拦截所有请求)
		.excludePathPatterns("/index.html","/","/user/login","/user/signOut"  //定义排出拦截哪些请求
               ,"/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg"
               ,"/**/*.ico","/**/*.svg");
		
	}
	
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		//指定filter在哪些url起作用
		List<String> urls = new ArrayList<>();
		//urls.add("/*");//对所有的路径起作用
		urls.add("/user/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	
	
}
