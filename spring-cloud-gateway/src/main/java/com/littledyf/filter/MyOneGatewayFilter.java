package com.littledyf.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @Author dengyifan
 * @create 2023/11/2 9:58
 * @description
 */
@Component
public class MyOneGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        String path = exchange.getRequest().getPath().toString();
        URI uri = exchange.getRequest().getURI();
        System.err.println(String.format("获取到请求路径：%s", uri));
        // 如果请求路径以“/v1”开头，则截取掉第一个路径段
        if (path.startsWith("/v1")) {
            path = path.substring("/v1".length());
        }

        // 创建新的请求对象，并将新路径设置为请求路径
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .path(path)
                .build();

        // 使用新请求对象创建新的ServerWebExchange对象
        ServerWebExchange newExchange = exchange.mutate()
                .request(newRequest)
                .build();
        System.err.println(String.format("获取到新的请求路径：%s", newExchange.getRequest().getURI()));
        // 继续执行过滤器链
        return chain.filter(newExchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
