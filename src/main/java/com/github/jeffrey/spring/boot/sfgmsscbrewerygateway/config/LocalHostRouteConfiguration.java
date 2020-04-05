package com.github.jeffrey.spring.boot.sfgmsscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jeffreymzd on 4/5/20
 */
@Configuration
public class LocalHostRouteConfiguration {

    @Bean
    public RouteLocator localHostRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                .uri("http://localhost:8080")
                .id("beer-service"))
                .build();
    }
}
