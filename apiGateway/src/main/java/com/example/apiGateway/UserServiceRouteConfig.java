package com.example.apiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceRouteConfig {

    @Bean
    RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r
                .path("/api/users/**")
                .filters(f -> f.rewritePath("/api/users/(?<segment>.*)", "/${segment}"))
                .uri("http://localhost:8083"))
            .build();
    }
}