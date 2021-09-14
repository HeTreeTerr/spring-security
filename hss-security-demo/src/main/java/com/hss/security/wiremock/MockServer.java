package com.hss.security.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;


/**
 * 
 * @author lenovo
 *
 */
public class MockServer {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("6666");
		//指定端口
		configureFor(8080);
		//清除所有配置
		//removeAllMappings();		
		stubFor(get(urlPathEqualTo("/order/1"))
				.willReturn(aResponse()
				.withBody("{\"id\":1}")
				.withStatus(200)
				));

	}
}
