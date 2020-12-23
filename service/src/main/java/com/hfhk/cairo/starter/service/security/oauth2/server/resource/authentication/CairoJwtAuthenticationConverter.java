package com.hfhk.cairo.starter.service.security.oauth2.server.resource.authentication;

import com.hfhk.auth.client.AuthenticationBasicClient;
import com.hfhk.cairo.security.authentication.RemoteUser;
import com.hfhk.cairo.security.oauth2.server.resource.authentication.CairoAuthentication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class CairoJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
	private final AuthenticationBasicClient authenticationBasicClient;

	public CairoJwtAuthenticationConverter(AuthenticationBasicClient authenticationBasicClient) {
		this.authenticationBasicClient = authenticationBasicClient;
	}

	@Override
	public CairoAuthentication convert(Jwt source) {
		RemoteUser remoteUser = authenticationBasicClient.authentication("Bearer " + source.getTokenValue());
		return new CairoAuthentication(source,
			Optional.ofNullable(remoteUser.getAuthorities())
				.stream()
				.flatMap(Collection::stream)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()),
			remoteUser.getUser()
		);
	}
}
