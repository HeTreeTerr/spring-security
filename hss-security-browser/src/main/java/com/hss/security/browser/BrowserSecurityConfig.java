/**
 * 
 */
package com.hss.security.browser;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.hss.security.browser.authentication.HssAuthenticationFailureHandler;
import com.hss.security.browser.authentication.HssAuthenticationSuccessHandler;
import com.hss.security.core.properties.SecurityProperties;
import com.hss.security.core.validate.code.ValidateCodeFilter;

/**
 * @author lenovo
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties; 
	
	@Autowired
	private HssAuthenticationSuccessHandler hssAuthenticationSuccessHandler;
	
	@Autowired
	private HssAuthenticationFailureHandler hssAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//推荐使用该（spring_security自带）加密方式
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		
		//todo 启动的时候创建表
		//tokenRepository.setCreateTableOnStartup(true);
		
		return tokenRepository;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(hssAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
		//http.httpBasic()//默认方式，在页面弹出登录框
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()//指定身份认证：表单认证
				.loginPage("/authentication/require")//自定义登录界面
				.loginProcessingUrl("/security/form")
				.successHandler(hssAuthenticationSuccessHandler)//表单提交成功处理方式
				.failureHandler(hssAuthenticationFailureHandler)
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.and()
		        //访问/logout请求（post），清空session,cookie,数据库记录，实现退出功能	
			.authorizeRequests()//以下都是授权的配置
			.antMatchers("/authentication/require",
					securityProperties.getBrowser().getLoginPage()
					,"/code/image")
			.permitAll()	//遇到此路径，允许访问
			.anyRequest()//任何请求
			.authenticated()
			.and()
			.csrf().disable()//关闭分布式防护
			;//需要身份认证
		
	}
}
