package com.rekomind.resource.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rekomind.resource")
public class ResourceWSApp {

	public static void main(final String[] args) {
		SpringApplication.run(ResourceWSApp.class, args);
	}
}
