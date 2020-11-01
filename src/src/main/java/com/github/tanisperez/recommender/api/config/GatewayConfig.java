package com.github.tanisperez.recommender.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Value("${gateway.recomendador-ws.id}")
	private String recomendadorWSId;
	@Value("${gateway.recomendador-ws.path}")
	private String recomendadorWSPath;
	@Value("${gateway.recomendador-ws.endpoint}")
	private String recomendadorWSEndpoint;
	@Value("${gateway.recomendador.filter.regex}")
	private String recomendadorWSFilterRegex;
	@Value("${gateway.recomendador.filter.replacement}")
	private String recomendadorWSFilterReplacement;

	@Value("${gateway.resource-ws.id}")
	private String resourceWSId;
	@Value("${gateway.resource-ws.path}")
	private String resourceWSPath;
	@Value("${gateway.resource-ws.endpoint}")
	private String resourceWSEndpoint;
	@Value("${gateway.resource-ws.filter.regex}")
	private String resourceWSFilterRegex;
	@Value("${gateway.resource-ws.filter.replacement}")
	private String resourceWSFilterReplacement;

	@Bean
	public RouteLocator createRoutes(final RouteLocatorBuilder builder) {
		// @formatter:off
		return builder.routes()
			.route(predicate -> predicate.path(this.recomendadorWSPath)
					.filters(filter -> filter.rewritePath(this.recomendadorWSFilterRegex, this.recomendadorWSFilterReplacement))
					.uri(this.recomendadorWSEndpoint)
					.id(this.recomendadorWSId)
				)
				.route(predicate -> predicate.path(this.resourceWSPath)
					.filters(filter -> filter.rewritePath(this.resourceWSFilterRegex, this.resourceWSFilterReplacement))
					.uri(this.resourceWSEndpoint)
					.id(this.resourceWSId)
				)
				.build();
		// @formatter:on

	}
}
