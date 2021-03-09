package com.lijiajia3515.cairo.starter.service.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijiajia3515.cairo.auth.modules.auth.client.AuthenticationClient;
import com.lijiajia3515.cairo.security.oauth2.server.resource.web.CairoBearerTokenAccessDeniedHandler;
import com.lijiajia3515.cairo.security.oauth2.server.resource.web.CairoBearerTokenAuthenticationEntryPoint;
import com.lijiajia3515.cairo.starter.service.security.oauth2.server.resource.authentication.CairoJwtAuthenticationConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

@Configuration
public class CairoSecurityOAuth2Configuration {

	@Bean
	@ConditionalOnMissingBean
	public BearerTokenResolver hfhkBearerTokenResolver() {
		DefaultBearerTokenResolver resolver = new DefaultBearerTokenResolver();
		resolver.setAllowFormEncodedBodyParameter(true);
		resolver.setAllowUriQueryParameter(true);
		return resolver;
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(ObjectMapper.class)
	public static class HandlerConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public CairoBearerTokenAuthenticationEntryPoint cairoBearerTokenAuthenticationEntryPoint(ObjectMapper objectMapper) {
			return new CairoBearerTokenAuthenticationEntryPoint()
				.realmName("lijiajia3515")
				.objectMapper(objectMapper);
		}

		@Bean
		@ConditionalOnMissingBean
		public CairoBearerTokenAccessDeniedHandler cairoBearerTokenAccessDeniedHandler(ObjectMapper objectMapper) {
			return new CairoBearerTokenAccessDeniedHandler()
				.realmName("lijiajia3515")
				.objectMapper(objectMapper);
		}

	}

	@Configuration(proxyBeanMethods = false)
	public static class ResourceServerConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public Converter<Jwt, AbstractAuthenticationToken> cairoJwtAuthenticationConverter(AuthenticationClient authBasicClient) {
			return new CairoJwtAuthenticationConverter(authBasicClient);
		}
	}


	// @Bean
	// public SecurityExpressionHandler<FilterInvocation> cairoWebSecurityExpressionHandler() {
	// 	return new CairoWebSecurityExpressionHandler();
	// }

	// @Bean
	// public MethodSecurityExpressionHandler cairoMethodSecurityExpressionHandler() {
	// 	return new CairoMethodSecurityExpressionHandler();
	// }

}
