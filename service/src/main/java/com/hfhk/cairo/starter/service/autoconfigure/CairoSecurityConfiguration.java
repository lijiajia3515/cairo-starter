package com.hfhk.cairo.starter.service.autoconfigure;

import com.hfhk.cairo.auth.client.AuthenticationBasicClient;
import com.hfhk.cairo.security.oauth2.expression.CairoMethodSecurityExpressionHandler;
import com.hfhk.cairo.security.oauth2.expression.CairoWebSecurityExpressionHandler;
import com.hfhk.cairo.starter.service.security.oauth2.server.resource.authentication.CairoJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;

@Configuration
public class CairoSecurityConfiguration {

	@Bean
	public CairoJwtAuthenticationConverter cairoJwtAuthenticationConverter(AuthenticationBasicClient authenticationBasicClient) {
		return new CairoJwtAuthenticationConverter(authenticationBasicClient);
	}

	@Bean
	public SecurityExpressionHandler<FilterInvocation> cairoWebSecurityExpressionHandler() {
		return new CairoWebSecurityExpressionHandler();
	}

	@Bean
	public MethodSecurityExpressionHandler cairoMethodSecurityExpressionHandler() {
		return new CairoMethodSecurityExpressionHandler();
	}

}
