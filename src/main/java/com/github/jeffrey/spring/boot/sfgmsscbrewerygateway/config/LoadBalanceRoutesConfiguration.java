package com.github.jeffrey.spring.boot.sfgmsscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by jeffreymzd on 4/5/20
 */
@Profile("local-discovery")
@Configuration
public class LoadBalanceRoutesConfiguration {

    @Bean
    public RouteLocator loadBalanceRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                        .uri("lb://beer-service")
                        .id("beer-service"))
                .route(r -> r.path("/api/v1/customers*", "/api/v1/customers/**")
                        .uri("lb://order-service")
                        .id("beer-order-service"))
                .route(r -> r.path("/api/v1/**")
                        .uri("lb://inventory-service")
                        .id("beer-inventory-service"))
                .route(r -> r.path("/api/v1/**")
                        .uri("lb://inventory-failover")
                        .id("beer-inventory-failover-service"))
                .build();
    }
}
