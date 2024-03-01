package com.lcwd.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;

@Configuration
@EnableWebFluxSecurity

public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

		httpSecurity.
		authorizeExchange()
		.anyExchange()
		.authenticated()
		.and().oauth2Client()
		.and()
		.oauth2ResourceServer()
				.jwt();

		return httpSecurity.build();

	}

	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;

	@Bean
	public ReactiveJwtDecoder reactiveJwtDecoder() {
		return NimbusReactiveJwtDecoder.withJwkSetUri(jwkSetUri).build();
	}

}