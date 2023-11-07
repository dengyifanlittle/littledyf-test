package com.littledyf.config;

import com.littledyf.filter.MyOneGatewayFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author dengyifan
 * @create 2023/11/2 10:00
 * @description
 */
@Configuration
public class GatewayConfig {

    @Value("${server.port}")
    private String port;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/v1/**")
                        .filters(f -> f.filter(new MyOneGatewayFilter()))
                        .uri("http://localhost:" + port)
                )
                .build();
    }

}

