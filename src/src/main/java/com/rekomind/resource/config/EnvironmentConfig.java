package com.rekomind.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.rekomind.common.audit.property.PropertyLogger;

/**
 * Environment Config using maven profiles.
 */
@Configuration
@PropertySource(value = "classpath:/${info.app.env.id}/application-${info.app.env.id}.properties")
public class EnvironmentConfig {

	/**
	 * Create a {@code PropertyLogger} bean to print by console every property
	 * configured.
	 *
	 * @return New instance of {@code PropertyLogger}.
	 */
	@Bean
	public PropertyLogger propertyLogger() {
		return new PropertyLogger();
	}

}