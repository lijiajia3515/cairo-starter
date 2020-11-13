package com.hfhk.harpoon.starter.service.autoconfigure;

import com.hfhk.cairo.auth.client.AuthenticationBasicClient;
import com.hfhk.cairo.security.oauth2.expression.CairoMethodSecurityExpressionHandler;
import com.hfhk.cairo.security.oauth2.expression.CairoWebSecurityExpressionHandler;
import com.hfhk.harpoon.starter.service.security.oauth2.server.resource.authentication.CairoJwtAuthenticationConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;

@Configuration
public class CairoSecurityConfiguration {

	@Bean
	@ConditionalOnBean(AuthenticationBasicClient.class)
	public CairoJwtAuthenticationConverter harpoonJwtAuthenticationConverter(AuthenticationBasicClient authenticationBasicClient) {
		return new CairoJwtAuthenticationConverter(authenticationBasicClient);
	}

	@Bean
	public SecurityExpressionHandler<FilterInvocation> harpoonWebSecurityExpressionHandler() {
		return new CairoWebSecurityExpressionHandler();
	}

	@Bean
	public MethodSecurityExpressionHandler harpoonMethodSecurityExpressionHandler() {
		return new CairoMethodSecurityExpressionHandler();
	}

}
