package com.lijiajia3515.cairo.sample.modules.test;

import com.lijiajia3515.cairo.auth.modules.auth.client.AuthenticationClient;
import com.lijiajia3515.cairo.starter.service.web.handler.query.RequestMessageParam;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/test")
public class TestApi {
	private final AuthenticationClient client;

	public TestApi(AuthenticationClient client) {
		this.client = client;
	}

	@RequestMapping
	@PermitAll
	public Object test() {
		return client.authentication("");
	}

	@RequestMapping("/b")
	@PermitAll
	public Object test(@RequestMessageParam @Validated Param param) {
		return param;
	}

	@Data
	@Accessors(chain = true)
	public static class Param {
		@NotNull
		private String a;
		@NotNull
		private String b;
	}
}
