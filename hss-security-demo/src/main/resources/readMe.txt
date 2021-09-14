1.restful文档自动生成工具
	访问文档：
	http://localhost:8080/swagger-ui.html
2.WireMock模拟rest服务

3.github项目版本管理工具（项目托管平台）

4.spring_security
	(1)默认干的事：
		保护所有请求，要访问需要账号(默认：user)、密码(自动生成一个)验证
	(2)SpringSecurity基本原理
		SpringSecurity的实质是一群过滤器链
		过滤器种类：
			1、UsernamePasswordAuthenticationFilter
			  BaseAuthenticationFilter//负责尝试验证用户输入信息
			2、EcceptionTranslationFilter//验证失败时负责抛出响应的异常
			...
			3、FilterSecurityInterceptor//最后一层的验证，负责判断
5.PasswordEncoder负责密码的加密解密
	String encode(CharSequence rawPassword);//负责加密
	boolean matches(CharSequence rawPassword, String encodedPassword)//负责比较传入值与加密后的值是否相同