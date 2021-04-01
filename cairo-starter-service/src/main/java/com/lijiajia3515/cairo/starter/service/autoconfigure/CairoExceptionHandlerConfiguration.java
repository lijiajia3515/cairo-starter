package com.lijiajia3515.cairo.starter.service.autoconfigure;

import com.lijiajia3515.cairo.core.business.DefaultBusiness;
import com.lijiajia3515.cairo.core.exception.BusinessException;
import com.lijiajia3515.cairo.core.result.BusinessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j(topic = "[ExceptionHandler]")
@RestControllerAdvice
@Configuration
public class CairoExceptionHandlerConfiguration {

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public BusinessResult<Object> statusException(BusinessException e, HttpServletRequest request, HttpServletResponse response) {
		log.info("[Exception] url-> [{}]", request.getRequestURI());
		log.info("[Exception]", e);
		HttpStatus status = (e.getStatus().success()) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		response.setStatus(status.value());
		return BusinessResult.build(e.getStatus(), e.getData());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public BusinessResult<Object> runtimeException(RuntimeException e, HttpServletRequest request) {
		log.info("[RuntimeException] url-> [{}]", request.getRequestURI());
		log.info("[RuntimeException]", e);
		return BusinessResult.buildFailed();
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BusinessResult<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
		log.info("[HttpRequestMethodNotSupportedException] url-> [{}]", request.getRequestURI());
		log.info("[HttpRequestMethodNotSupportedException]", e);
		return new BusinessResult<>(false, DefaultBusiness.Failed.code(), e.getMessage(), e.getMethod());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public BusinessResult<Object> exception(Exception e, HttpServletRequest request) {
		log.info("[Exception] url-> [{}]", request.getRequestURI());
		log.info("[Exception]", e);
		return BusinessResult.buildUnknown();
	}

}
