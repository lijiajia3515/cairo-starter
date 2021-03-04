package com.lijiajia3515.cairo.starter.service.security.oauth2.server.resource.authentication;

import com.hfhk.auth.modules.auth.client.AuthBasicClient;
import com.hfhk.cairo.security.authentication.RemoteUser;
import com.hfhk.cairo.security.oauth2.server.resource.authentication.CairoAuthentication;
import com.hfhk.cairo.security.oauth2.user.AuthPrincipal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class CairoJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
	private final AuthBasicClient authBasicClient;

	public CairoJwtAuthenticationConverter(AuthBasicClient authBasicClient) {
		this.authBasicClient = authBasicClient;
	}

	@Override
	public CairoAuthentication convert(Jwt source) {
		RemoteUser remoteUser = authBasicClient.auth("Bearer " + source.getTokenValue());
		;
		return new CairoAuthentication(new AuthPrincipal(source, remoteUser.getUser()),
			Optional.ofNullable(remoteUser.getAuthorities())
				.stream()
				.flatMap(Collection::stream)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList())
		);
	}
}
