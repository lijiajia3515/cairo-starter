package com.hfhk.cairo.starter.mongo.auditing;


import com.hfhk.cairo.domain.auth.User;
import com.hfhk.cairo.security.oauth2.server.resource.authentication.CairoAuthentication;
import com.hfhk.cairo.security.oauth2.user.AuthPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UidAuditorWare implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
			.flatMap(authentication -> {
				if (authentication instanceof CairoAuthentication) {
					return Optional.ofNullable(((CairoAuthentication) authentication).getPrincipal())
						.map(AuthPrincipal::getUser)
						.map(User::getUid);
				}
				if (authentication instanceof AnonymousAuthenticationToken) {
					return Optional.ofNullable(authentication.getPrincipal().toString());
				}
				return Optional.ofNullable(authentication.getPrincipal().toString());
			});
	}
}
