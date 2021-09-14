/**
 * 
 */
package com.hss.security.exception;

/**
 * @author lenovo
 *
 */
public class UserNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -942529713733115462L;
	
	private String id;
	
	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
