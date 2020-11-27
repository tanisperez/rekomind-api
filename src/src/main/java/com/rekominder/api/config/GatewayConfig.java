package com.rekominder.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "*";
	private static final String ACCESS_CONTROL_ALLOW_METHODS = "GET, POST, PUT, DELETE";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Content-Type,Authorization,Cache-Control,Pragma,Expires";
	private static final String ACCESS_CONTROL_MAX_AGE = "86400"; // seconds
	private static final String XFRAME_OPTIONS = "DENY";

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
					.filters(filter -> {
						addResponseHeaders(filter);
						return filter.rewritePath(this.recomendadorWSFilterRegex, this.recomendadorWSFilterReplacement);
					})
					.uri(this.recomendadorWSEndpoint)
					.id(this.recomendadorWSId)
				)
				.route(predicate -> predicate.path(this.resourceWSPath)
					.filters(filter -> {
						addResponseHeaders(filter);
						return filter.rewritePath(this.resourceWSFilterRegex, this.resourceWSFilterReplacement);
					})
					.uri(this.resourceWSEndpoint)
					.id(this.resourceWSId)
				)
				.build();
		// @formatter:on
	}

	private static void addResponseHeaders(final GatewayFilterSpec filter) {
		filter.addResponseHeader("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN);
		filter.addResponseHeader("Access-Control-Allow-Methods", ACCESS_CONTROL_ALLOW_METHODS);
		filter.addResponseHeader("Access-Control-Allow-Headers", ACCESS_CONTROL_ALLOW_HEADERS);
		filter.addResponseHeader("Access-Control-Max-Age", ACCESS_CONTROL_MAX_AGE);
		filter.addResponseHeader("X-Frame-Options", XFRAME_OPTIONS);
	}
}
