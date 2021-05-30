package com.rekomind.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Rekomind API App
 */
@SpringBootApplication(scanBasePackages = "com.rekomind.api")
public class RekomindAPIApp {

	public static void main(final String[] args) {
		SpringApplication.run(RekomindAPIApp.class, args);
	}
}
