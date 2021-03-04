package com.lijiajia3515.cairo.sample.config;

import com.lijiajia3515.cairo.starter.service.web.handler.BusinessResultReturnValueHandler;
import com.lijiajia3515.cairo.starter.service.web.handler.query.RequestMessageParamMethodProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import java.util.Collections;
import java.util.List;

@Slf4j

@Configuration(proxyBeanMethods = false)
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private final BusinessResultReturnValueHandler resultReturnValueHandler;
	private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	private final RequestMessageParamMethodProcessor requestMessageParamMethodProcessor;


	public WebConfig(BusinessResultReturnValueHandler resultResponseHandler,
					 MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
					 List<HttpMessageConverter<?>> messageConverters) {
		this.resultReturnValueHandler = resultResponseHandler;
		this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
		this.requestMessageParamMethodProcessor = new RequestMessageParamMethodProcessor(
			messageConverters,
			new ContentNegotiationManager(),
			Collections.singletonList(new JsonViewRequestBodyAdvice())
		);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		log.info("Configured Spring Mvc Argument Resolvers");
		resolvers.add(requestMessageParamMethodProcessor);
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		log.debug("Configured Spring Mvc Return Value Handlers");
		handlers.add(resultReturnValueHandler);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.info("Configured Spring Mvc Message Converters");
		converters.add(mappingJackson2HttpMessageConverter);
	}
}
