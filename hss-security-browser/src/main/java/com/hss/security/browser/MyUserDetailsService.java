/**
 * 
 */
package com.hss.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lenovo
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 * 获取前台传入的用户名
	 * 由用户名从数据库中查询信息，进行账户信息获取
	 * 将传入的信息与数据库中查询的信息对比，验证信息的正确性
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名"+username);
		String passWord = passwordEncoder.encode("123456");//加密（注册时先加密后写入数据库）
		logger.info("加密后密码"+passWord);
		//根据用户名查找用户信息
		//return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return new User(username  //用户名
					, passWord	//密码
					, true	//可用
					, true	//账号没有过期
					, true	//密码没有过期
					, true	//没有假删除（用户帐号已被锁定）
					, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
