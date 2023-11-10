package com.littledyf.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @Author dengyifan
 * @create 2023/11/7 14:12
 * @description
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        System.err.println(String.format("全局过滤器获取到请求路径：%s", exchange.getRequest().getURI()));
        String test = exchange.getRequest().getHeaders().getFirst("test");
        // 无test请求头
//        if (!Optional.ofNullable(test).isPresent()) {
//            System.err.println("无指定请求头");
//            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//            return exchange.getResponse().setComplete();
//        }
        System.err.println(String.format("请求头参数:%s", test));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
