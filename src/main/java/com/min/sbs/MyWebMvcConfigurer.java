package com.min.sbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.min.sbs.interceptor.BeforeInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	@Autowired
	BeforeInterceptor beforeInterceptor = new BeforeInterceptor();
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeInterceptor).addPathPatterns("/**").excludePathPatterns("/resources/**").excludePathPatterns("/error");
	}
}
