package com.lijiajia3515.cairo.starter.service.security.oauth2.server.resource.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijiajia3515.cairo.security.authentication.RemoteUser;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthenticationClient {
	/*private final OkHttpClient client;
	private final ObjectMapper objectMapper;

	@Getter
	@Setter
	private String server;

	@Getter
	@Setter
	private String url = "/authentication";

	public AuthenticationClient(OkHttpClient client, ObjectMapper objectMapper) {
		this.client = client;
		this.objectMapper = objectMapper;
	}

	public RemoteUser authentication(String accessToken) {
		Request request = new Request.Builder().url(server.concat(url))
			.addHeader(AUTHORIZATION, "Bearer ".concat(accessToken))
			.build();
		Call call = client.newCall(request);
		try {
			Response execute = call.execute();
			if (execute.body() == null) {
				return null;
			}
			return objectMapper.readValue(execute.body().string(), RemoteUser.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		AuthenticationClient authenticationClient = new AuthenticationClient(new OkHttpClient(), new ObjectMapper());
		authenticationClient.setServer("http://auth.hfhksoft.com");

		RemoteUser authentication = authenticationClient.authentication("12345");
		System.out.println(authentication);
	}*/
}
