package com.hss.security.core.validate.code;
/**
 * 
 * @author lenovo
 *
 */

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

	//图片
	private BufferedImage image;
	//验证码的值
	private String code;
	//过期时间
	private LocalDateTime expireTime;
	
	public ImageCode(BufferedImage image, String code, int expireIn) {
		
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		
		this.image = image;
		this.code = code;
		this.expireTime = expireTime;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		
		return LocalDateTime.now().isAfter(expireTime);
	}
	
	
}
