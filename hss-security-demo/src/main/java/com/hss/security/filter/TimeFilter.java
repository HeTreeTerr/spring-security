/**
 * 
 */
package com.hss.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @author lenovo
 * 	过滤器
 *	Filter只能获取request和response
 *	只能从request和response中获取一些参数
 *	不知道请求处理的详细过程（如何处理，由谁处理等）
 *	j2ee规范的
 */
public class TimeFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("TimeFilter init");

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("TimeFilter start");
		long start = new Date().getTime();
		chain.doFilter(request, response);
		long end = new Date().getTime();
		System.out.println("TimeFilter:"+(end-start));
		System.out.println("TimeFilter finish");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("TimeFilter destroy");

	}

}
