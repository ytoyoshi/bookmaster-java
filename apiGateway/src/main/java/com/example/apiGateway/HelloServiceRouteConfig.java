package com.example.apiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloServiceRouteConfig {

    @Bean
    RouteLocator helloServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("hello-service", r -> r
                .path("/api/hello/**")
                .filters(f -> f.rewritePath("/api/hello/(?<segment>.*)", "/${segment}"))
                .uri("http://localhost:8081"))
            .build();
    }
}