package com.rekominder.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rekominder.api")
public class RekominderAPI {

	public static void main(final String[] args) {
		SpringApplication.run(RekominderAPI.class, args);
	}
}
