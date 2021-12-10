package com.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class interceptorconfig  implements WebMvcConfigurer{
	@Autowired
	com.edu.interceptor.Globallnterceptor globallnterceptor;
 
 @Override
public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(globallnterceptor)
	.addPathPatterns("/**")
	.excludePathPatterns("/rest/**","/admin/**","/assets/**"); // bỏ url mà ko cho nó quyền truy cập
}
}
