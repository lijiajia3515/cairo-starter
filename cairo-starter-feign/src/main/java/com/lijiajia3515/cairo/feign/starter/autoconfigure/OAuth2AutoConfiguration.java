package com.lijiajia3515.cairo.feign.starter.autoconfigure;

import com.lijiajia3515.cairo.feign.starter.properties.FeignClientOAuth2Properties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2AutoConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "lijiajia3515.feign.oauth2")
	public FeignClientOAuth2Properties serviceOAuth2Properties() {
		return new FeignClientOAuth2Properties();
	}

	@Bean
	@ConditionalOnBean(FeignClientOAuth2Properties.class)
	public OAuth2RestTemplate oAuth2RestTemplate(FeignClientOAuth2Properties properties) {
		final ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setClientId(properties.getClientId());
		resource.setClientSecret(properties.getClientSecret());
		resource.setScope(properties.getScopes());
		resource.setGrantType(properties.getGrantType());
		resource.setAccessTokenUri(properties.getAccessTokenUri());
		resource.setAuthenticationScheme(properties.getAuthenticationScheme());
		OAuth2ClientContext context = new DefaultOAuth2ClientContext();
		return new OAuth2RestTemplate(resource, context);
	}
}
