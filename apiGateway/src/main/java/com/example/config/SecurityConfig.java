package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		return http
	            .csrf(csrf -> csrf.disable())
	            .authorizeExchange(auth -> auth
	                .pathMatchers("/api/users/register", "/api/users/login").permitAll()
	                .anyExchange().authenticated()
	            )
	            .build();
	}

}
