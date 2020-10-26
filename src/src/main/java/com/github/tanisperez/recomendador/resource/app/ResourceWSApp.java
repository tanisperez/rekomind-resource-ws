package com.github.tanisperez.recomendador.resource.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.tanisperez.recomendador.resource")
public class ResourceWSApp {

	public static void main(final String[] args) {
		SpringApplication.run(ResourceWSApp.class, args);
	}
}
