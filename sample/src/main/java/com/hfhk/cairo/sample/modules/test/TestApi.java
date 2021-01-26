package com.hfhk.cairo.sample.modules.test;

import com.hfhk.auth.modules.auth.client.AuthBasicClient;
import com.hfhk.cairo.starter.service.web.handler.query.RequestMessageParam;
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
	private final AuthBasicClient client;

	public TestApi(AuthBasicClient client) {
		this.client = client;
	}

	@RequestMapping
	@PermitAll
	public Object test() {
		return client.auth("");
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
