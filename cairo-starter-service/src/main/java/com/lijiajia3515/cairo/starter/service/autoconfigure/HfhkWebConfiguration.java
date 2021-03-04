package com.lijiajia3515.cairo.starter.service.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijiajia3515.cairo.starter.service.web.handler.BusinessResultReturnValueHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
public class HfhkWebConfiguration {


	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(ObjectMapper.class)
	public static class ReturnValueConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public BusinessResultReturnValueHandler resultResponseHandler(ObjectMapper objectMapper) {
			return new BusinessResultReturnValueHandler(objectMapper);
		}
	}


}
