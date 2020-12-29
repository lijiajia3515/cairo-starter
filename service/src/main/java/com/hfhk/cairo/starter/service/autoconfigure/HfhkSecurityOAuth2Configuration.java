package com.hfhk.cairo.starter.service.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfhk.auth.client.AuthBasicClient;
import com.hfhk.cairo.security.oauth2.server.resource.web.CairoBearerTokenAccessDeniedHandler;
import com.hfhk.cairo.security.oauth2.server.resource.web.CairoBearerTokenAuthenticationEntryPoint;
import com.hfhk.cairo.starter.service.security.oauth2.server.resource.authentication.CairoJwtAuthenticationConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

@Configuration
public class HfhkSecurityOAuth2Configuration {

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
		public CairoBearerTokenAuthenticationEntryPoint hfhkBearerTokenAuthenticationEntryPoint(ObjectMapper objectMapper) {
			return new CairoBearerTokenAuthenticationEntryPoint()
				.realmName("hfhk")
				.objectMapper(objectMapper);
		}

		@Bean
		@ConditionalOnMissingBean
		public CairoBearerTokenAccessDeniedHandler hfhkBearerTokenAccessDeniedHandler(ObjectMapper objectMapper) {
			return new CairoBearerTokenAccessDeniedHandler()
				.realmName("hfhk")
				.objectMapper(objectMapper);
		}

	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(AuthBasicClient.class)
	public static class ResourceServerConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public CairoJwtAuthenticationConverter cairoJwtAuthenticationConverter(AuthBasicClient authBasicClient) {
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
