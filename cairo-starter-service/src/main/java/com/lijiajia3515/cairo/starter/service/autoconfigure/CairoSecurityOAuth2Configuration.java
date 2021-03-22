package com.lijiajia3515.cairo.starter.service.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijiajia3515.cairo.auth.modules.auth.client.AuthenticationClient;
import com.lijiajia3515.cairo.security.oauth2.server.resource.web.CairoBearerTokenAccessDeniedHandler;
import com.lijiajia3515.cairo.security.oauth2.server.resource.web.CairoBearerTokenAuthenticationEntryPoint;
import com.lijiajia3515.cairo.starter.service.security.oauth2.server.resource.authentication.CairoJwtAuthenticationConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	public static class Handler {

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
	public static class ResourceServer {

		@Bean
		@ConditionalOnMissingBean
		public CairoJwtAuthenticationConverter cairoJwtAuthenticationConverter(ObjectProvider<AuthenticationClient> client) {
			return new CairoJwtAuthenticationConverter(client.getIfAvailable());
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
