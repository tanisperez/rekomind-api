package com.rekomind.api.config;

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

	@Value("${gateway.rekomind-core-ws.id}")
	private String rekomindCoreWSId;
	@Value("${gateway.rekomind-core-ws.path}")
	private String rekomindCoreWSPath;
	@Value("${gateway.rekomind-core-ws.endpoint}")
	private String rekomindCoreWSEndpoint;
	@Value("${gateway.rekomind-core-ws.filter.regex}")
	private String rekomindCoreWSFilterRegex;
	@Value("${gateway.rekomind-core-ws.filter.replacement}")
	private String rekomindCoreWSFilterReplacement;

	@Value("${gateway.rekomind-resource-ws.id}")
	private String rekomindResourceWSId;
	@Value("${gateway.rekomind-resource-ws.path}")
	private String rekomindResourceWSPath;
	@Value("${gateway.rekomind-resource-ws.endpoint}")
	private String rekomindResourceWSEndpoint;
	@Value("${gateway.rekomind-resource-ws.filter.regex}")
	private String rekomindResourceWSFilterRegex;
	@Value("${gateway.rekomind-resource-ws.filter.replacement}")
	private String rekomindResourceWSFilterReplacement;

	@Bean
	public RouteLocator createRoutes(final RouteLocatorBuilder builder) {
		// @formatter:off
		return builder.routes()
			.route(predicate -> predicate.path(this.rekomindCoreWSPath)
					.filters(filter -> {
						addResponseHeaders(filter);
						return filter.rewritePath(this.rekomindCoreWSFilterRegex, this.rekomindCoreWSFilterReplacement);
					})
					.uri(this.rekomindCoreWSEndpoint)
					.id(this.rekomindCoreWSId)
				)
				.route(predicate -> predicate.path(this.rekomindResourceWSPath)
					.filters(filter -> {
						addResponseHeaders(filter);
						return filter.rewritePath(this.rekomindResourceWSFilterRegex, this.rekomindResourceWSFilterReplacement);
					})
					.uri(this.rekomindResourceWSEndpoint)
					.id(this.rekomindResourceWSId)
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
