package com.github.jeffrey.spring.boot.sfgmsscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.codec.ServerCodecConfigurer;

/**
 * Created by jeffreymzd on 4/5/20
 */
@Profile("!local-discovery")
@Configuration
public class LocalHostRouteConfiguration {

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }
    @Bean
    public RouteLocator localHostRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                .uri("http://localhost:8080")
                .id("beer-service"))
                .route(r -> r.path("/api/v1/customers*", "/api/v1/customers/**")
                .uri("http://localhost:8081")
                .id("beer-order-service"))
                .route(r -> r.path("/api/v1/**")
                .uri("http://localhost:8082")
                .id("beer-inventory-service"))
                .route(r -> r.path("/inventory-failover")
                .uri("http://localhost:8083")
                .id("beer-inventory-failover-service"))
                .build();
    }
}
