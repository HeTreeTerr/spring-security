/**
 * 
 */
package com.hss.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lenovo
 *	@EnableSwagger2:标注在springboot启动程序上。开始swagger2的
 *	功能，生成restful的前台交接文档
 *	访问：http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	/**
	 * @param args
	 * springboot项目主函数
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class,args);
	}

}
