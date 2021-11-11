package com.lijiajia3515.cairo.feign.starter.properties;

import lombok.Data;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.List;


@Data
public class FeignClientOAuth2Properties {
	/**
	 * client id
	 */
	private String clientId;

	/**
	 * client secret
	 */
	private String clientSecret;

	/**
	 * 范围
	 */
	private List<String> scopes;

	/**
	 * token uri
	 */
	private String accessTokenUri;

	/**
	 * 授权类型
	 */
	private String grantType;

	/**
	 * 认证方式
	 */
	private AuthenticationScheme authenticationScheme;


}
