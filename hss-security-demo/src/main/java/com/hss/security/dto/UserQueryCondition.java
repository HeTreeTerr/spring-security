/**
 * 
 */
package com.hss.security.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lenovo
 *
 */
public class UserQueryCondition {
	
	private String username;
	
	private Integer age;
	
	private Integer ageto;
	
	@ApiModelProperty(value = "母鸡啊")
	private String xxx;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAgeto() {
		return ageto;
	}

	public void setAgeto(Integer ageto) {
		this.ageto = ageto;
	}

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	
	
}
