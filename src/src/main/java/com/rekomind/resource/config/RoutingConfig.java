package com.rekomind.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingConfig {

	@Value("${resource.routingLocation}")
	private String routingLocation;

	@Value("${resource.pathLocation}")
	private String pathLocation;

	@Bean
	public RouterFunction<ServerResponse> imageRouter() {
		return RouterFunctions.resources(this.routingLocation, new FileSystemResource(this.pathLocation));
	}
}
