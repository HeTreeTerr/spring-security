/**
 * 
 */
package com.hss.security.core.properties;

/**
 * @author lenovo
 *
 */
public class BrowserProperties {
	//不配置时默认跳转的页面
	private String loginPage = "/login.html";
	
	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;
	
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
	
}
