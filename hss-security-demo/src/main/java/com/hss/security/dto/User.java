/**
 * 
 */
package com.hss.security.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import com.hss.security.validadator.MyConstraint;
/**
 * @author lenovo
 *	jsonView使用：
 *	json字符串控制输出
 *	在对的场景显示对的数据
 */
public class User {
	//用户简单信息
	public interface UserSimpleView {};
	//用户详细信息，由于“extends UserSimpleView”，显示所有用户简单信息的字段
	public interface UserDetailView extends UserSimpleView {};
	
	@MyConstraint(message = "这是一个测试标签")
	private String username;
	
	@NotBlank(message = "密码不能为空")	//不为空
	private String password;
	
	private String id;
	
	@Past(message = "生日必须是过去的时间")	//生日必须是过去式的时间
	private Date birthday;
	
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", id=" + id + ", birthday=" + birthday + "]";
	}
	
	
}
