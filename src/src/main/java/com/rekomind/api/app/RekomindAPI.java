package com.rekomind.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rekomind.api")
public class RekomindAPI {

	public static void main(final String[] args) {
		SpringApplication.run(RekomindAPI.class, args);
	}
}
