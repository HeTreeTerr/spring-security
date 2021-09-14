/**
 * 
 */
package com.hss.security.dto;

/**
 * @author lenovo
 *
 */
public class FileInfo {
	
	public FileInfo(String path) {
		super();
		this.path = path;
	}

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
