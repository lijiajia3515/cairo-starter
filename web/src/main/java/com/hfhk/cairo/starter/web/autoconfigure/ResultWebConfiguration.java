package com.hfhk.cairo.starter.web.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfhk.cairo.starter.web.handler.BusinessResultReturnValueHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultWebConfiguration {

	@Bean
	public BusinessResultReturnValueHandler resultResponseHandler(ObjectMapper objectMapper) {
		return new BusinessResultReturnValueHandler(objectMapper);
	}

}
