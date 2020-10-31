package com.github.tanisperez.recommender.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.tanisperez.recommender.api")
public class RecommenderAPIApp {

	public static void main(final String[] args) {
		SpringApplication.run(RecommenderAPIApp.class, args);
	}
}
