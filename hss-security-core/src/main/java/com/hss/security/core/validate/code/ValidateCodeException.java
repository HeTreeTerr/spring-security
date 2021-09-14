package com.hss.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;
/**
 * 
 * @author lenovo
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 538807644433953101L;

	public ValidateCodeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
