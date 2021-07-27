package com.lijiajia3515.cairo.starter.service.web.handler.query;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMessageParam {
	/**
	 * 是否必须
	 *
	 * @return 是否必须
	 */
	boolean required() default true;

	/**
	 * 参数名称
	 *
	 * @return 名称
	 */
	String name() default "Param";
}
