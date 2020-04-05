package com.github.jeffrey.spring.boot.sfgmsscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by jeffreymzd on 4/5/20
 */
@Profile("google")
@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/googlesearch") // this url does not work
                        .filters(f -> f.rewritePath("/googlesearch(?<segment>/?.*)",
                                "/search${segment}")) // redirect to a working url
                        .uri("https://google.com")
                        .id("google"))
                .build();
    }
}
