package com.lijiajia3515.cairo.starter.mongodb.autoconfigure;

import com.lijiajia3515.cairo.starter.mongodb.auditing.UidAuditorWare;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@Configuration
public class MongoAuditorConfig {

	@Bean
	public UidAuditorWare uidAuditorWare() {
		return new UidAuditorWare();
	}
	
}
