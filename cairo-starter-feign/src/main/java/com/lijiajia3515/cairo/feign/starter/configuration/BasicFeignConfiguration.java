package com.lijiajia3515.cairo.feign.starter.configuration;

import com.lijiajia3515.cairo.feign.interceptor.BasicRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class BasicFeignConfiguration {

	@Bean
	public BasicRequestInterceptor requestInterceptor() {
		return new BasicRequestInterceptor();
	}
}

