package com.lijiajia3515.cairo.feign.starter.configuration;

import com.lijiajia3515.cairo.feign.interceptor.RequestOAuth2RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class RequestFeignConfiguration {

	@Bean
	public RequestOAuth2RequestInterceptor requestOAuth2RequestInterceptor() {
		return new RequestOAuth2RequestInterceptor();
	}
}
