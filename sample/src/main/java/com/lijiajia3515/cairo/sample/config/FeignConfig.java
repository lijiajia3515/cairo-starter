package com.lijiajia3515.cairo.sample.config;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@EnableFeignClients("com.lijiajia3515.cairo.**.client")
@Configuration
public class FeignConfig {

	@Bean
	public RequestInterceptor hfhkOAuth2FeignRequestInterceptor(OAuth2ClientContext context, OAuth2ProtectedResourceDetails details) {
		return new OAuth2FeignRequestInterceptor(context, details);
	}

}
