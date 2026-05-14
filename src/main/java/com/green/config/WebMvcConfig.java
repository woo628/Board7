package com.green.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.green.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
// authInterceptor를 동작시킬때 모든 페이지(/**)를 대상
	@Autowired
	private AuthInterceptor authInterceptor; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 로그인 대상 페이지
		registry.addInterceptor(authInterceptor)
		//	.addPathPatterns("/**") // 모든 파일 주소 /** 로
			.addPathPatterns("/Board/**","/BoardPaging/**") // Board 밑에 있는 주소만 /** 로
			.excludePathPatterns("/css/**","/img/**","/js/**"); // 제외
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
