package com.lijiajia3515.cairo.sample.config;

import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Collections;

@Configuration
public class OAuth2Config {

    /*@Bean
    public OAuth2ClientContext hfhkOAuth2ClientContext() {
        return new DefaultOAuth2ClientContext();
    }*/

	@Bean
	OAuth2ProtectedResourceDetails hfhkOAuth2ProtectedResourceDetails() {
		ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
		clientCredentialsResourceDetails.setId(IdUtil.randomUUID());
		clientCredentialsResourceDetails.setClientId("hfhk_dhgxjsj");
		clientCredentialsResourceDetails.setClientSecret("hfhk_dhgxjsj");
		clientCredentialsResourceDetails.setScope(Collections.singletonList("default"));
		clientCredentialsResourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
		clientCredentialsResourceDetails.setAccessTokenUri("http://auth.hfhksoft.com/oauth2/token");
		return clientCredentialsResourceDetails;
	}

}
