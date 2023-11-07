package com.littledyf.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.GatewayToStringStyler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @Author dengyifan
 * @create 2023/11/2 11:43
 * @description
 */
@Component
public class MyTwoGatewayFilterFactory extends AbstractGatewayFilterFactory<MyTwoGatewayFilterFactory.Config> {
    public MyTwoGatewayFilterFactory() {
        super(MyTwoGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }

    @Override
    public GatewayFilter apply(MyTwoGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                // 获取请求路径
                URI uri = exchange.getRequest().getURI();
                System.err.println(String.format("获取到请求路径：%s", uri));
                //
                System.err.println(String.format("配置属性：%s", config.getName()));
                String path = exchange.getRequest().getPath().toString();
                // 如果请求路径以“/xxx”开头，则截取掉第一个路径段,xxx是配置文件中的name属性
                if (path.startsWith("/" + config.getName())) {
                    path = path.substring(("/" + config.getName()).length());
                }else {
                    throw new IllegalStateException("CustomGatewayFilter is not enabled");
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

                return chain.filter(newExchange);
            }

            @Override
            public String toString() {
                return GatewayToStringStyler.filterToStringCreator(MyTwoGatewayFilterFactory.this).toString();
            }
        };
    }

    public static class Config {
        private String name;

        public Config() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
