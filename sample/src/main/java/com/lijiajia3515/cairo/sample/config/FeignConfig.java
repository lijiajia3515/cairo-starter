package com.lijiajia3515.cairo.sample.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(
	basePackages = {"com.lijiajia3515.**.client"}
)
@Configuration
public class FeignConfig {
}
