package com.hfhk.cairo.starter.service.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfhk.cairo.starter.service.web.handler.BusinessResultReturnValueHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HfhkWebConfiguration {

	@Configuration
	@ConditionalOnBean(ObjectMapper.class)
	static class ReturnValueConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public BusinessResultReturnValueHandler resultResponseHandler(ObjectMapper objectMapper) {
			return new BusinessResultReturnValueHandler(objectMapper);
		}
	}


}
