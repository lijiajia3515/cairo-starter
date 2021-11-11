package com.lijiajia3515.cairo.feign.starter.configuration;

import com.lijiajia3515.cairo.feign.interceptor.ClientOAuth2RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class ClientFeignConfiguration {

	@Bean
	@ConditionalOnBean(OAuth2RestTemplate.class)
	public ClientOAuth2RequestInterceptor clientOAuth2RequestInterceptor(OAuth2RestTemplate template) {
		return new ClientOAuth2RequestInterceptor(template);
	}
}
