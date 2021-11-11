package com.lijiajia3515.cairo.auth.modules.auth;

import com.lijiajia3515.cairo.feign.starter.configuration.BasicFeignConfiguration;
import com.lijiajia3515.cairo.security.authentication.RemoteUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "basic", name = "authenticationClient", url = "http://${lijiajia3515.auth.server:auth-server}", path = "/", configuration = BasicFeignConfiguration.class)
public interface AuthenticationClient {

	@GetMapping("/authentication")
	RemoteUser authentication(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authentication);
}
