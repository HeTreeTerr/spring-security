/**
 * 
 */
package com.hss.security.wiremock;

import java.io.IOException;

import org.aspectj.util.FileUtil;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.client.WireMock;



/**
 * @author lenovo
 *
 */
public class MockServer2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//通过端口连接服务
        WireMock.configureFor(9000);
        //清空之前的配置
        WireMock.removeAllMappings();

        //调用 封装方法
        mock("/user/2","user");
	}
	
	private static void mock(String url, String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("/wiremock1/"+filename+".txt");
        String content = FileUtil.readAsString(resource.getFile());

        //get请求
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse()
                        //body里面写 json
                        .withBody(content)
                        //返回状态码
                        .withStatus(200)));
    }

}
