package com.shopmax.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 파일을 읽어올 경로 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// @Value("${uplodaPath}")
	// String uplodaPath
	String uplodaPath = "file:///C:/shop/\r\n"; // 업로드 할 경로
	
	// 웹 브라우저에서 URL이 /images로 시작하는 경우 uplodaPath에 설정한 폴더를 기준으로 파일을 읽어온다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // 경로 우회
				.addResourceLocations(uplodaPath);
	}
	
	
}
