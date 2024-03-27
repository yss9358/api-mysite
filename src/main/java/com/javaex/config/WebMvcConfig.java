package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 해당주소로 왔을때 의 해당경로를 찾아가기 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**")
				.addResourceLocations("file:C:\\javaStudy\\uploadfile\\");
	}
	
	// api 주소로 오는 처리만 해결해주는 메소드
	@Override
	public void addCorsMappings (CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("http://localhost:8080")
				.allowedHeaders("*")
				.exposedHeaders("Authorization")
				.allowCredentials(true)
				;
	}
}
