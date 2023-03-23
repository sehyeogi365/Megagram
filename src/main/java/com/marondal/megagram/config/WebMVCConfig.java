package com.marondal.megagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.marondal.megagram.common.FileManagerService;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH + "/");//파일저장한 경로
		//api 와 아작스 와 설정 이모두를 모두 끊어서 천천히 해나간다 생각
	}
	
}
