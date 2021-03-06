package com.hss.security.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hss.security.dto.FileInfo;

/**
 * 
 * @author lenovo
 *
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {
	private String floder = "H:\\资料\\java\\我的java项目\\spring-security\\hss-security-demo\\src";
	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		File localFile = new File(floder ,new Date().getTime()+"txt");
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());		
	}
	
	@GetMapping(value = "/{id:\\d+}")
	public void download(@PathVariable String id , HttpServletRequest request,HttpServletResponse response) throws Exception {
		//jdk1.7新特性
		try (
			InputStream input = new FileInputStream(new File(floder, id+".txt"));
			OutputStream output = response.getOutputStream();
				){
				response.setContentType("application/x-download");	
				response.addHeader("Content-Disposition", "attachment;filename=test.txt");
				
				IOUtils.copy(input, output);
				
		}
		
	}
}
