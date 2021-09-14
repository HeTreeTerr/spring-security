/**
 * 
 */
package com.hss.security.core.properties;

/**
 * @author lenovo
 *
 */
public class ImageCodeProperties {
	//验证码宽度
	private int width = 120;
	//验证码高度
	private int height = 35;
	//验证码内容字符数
	private int length = 4;
	//验证码有效期
	private int expireIn = 60;
	
	String url;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
