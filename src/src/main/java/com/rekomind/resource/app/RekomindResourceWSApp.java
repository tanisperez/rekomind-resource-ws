package com.rekomind.resource.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Rekomind Resource WS App
 */
@SpringBootApplication(scanBasePackages = "com.rekomind.resource")
public class RekomindResourceWSApp {

	public static void main(final String[] args) {
		SpringApplication.run(RekomindResourceWSApp.class, args);
	}
}
