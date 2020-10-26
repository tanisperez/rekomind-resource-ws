package com.github.tanisperez.recomendador.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ImageConfig {

	@Bean
	public RouterFunction<ServerResponse> imgRouter() {
		return RouterFunctions.resources("/images/**", new FileSystemResource("/Users/tanis/Dev/Java/resource-ws/resources/"));
	}
}
