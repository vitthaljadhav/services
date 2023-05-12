package com.web.spring.cloud.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.build();
	}

	
	@Bean
	public RouteLocator gatewayRouter1(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/currencyExchange/**")
				.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
				.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feigen/**")
				.uri("lb://currency-conversion"))
				.build();
	}
}
