package com.marondal.megagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.marondal.megagram.common.FileManagerService;

@Configuration//설정을 위한 클래스
public class WebMVCConfig implements WebMvcConfigurer{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {//특정한 파일 폴더경로
		
		registry.addResourceHandler("/images/**")//url경로를 넣는다. 스태틱이기 때문에 객체 생성없이 바로이렇게 이름뒤에.하고 쓸수 있음
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH + "/");//파일저장한 경로
		//api 와 아작스 와 설정 이모두를 모두 끊어서 천천히 해나간다 생각
	}
	
}
