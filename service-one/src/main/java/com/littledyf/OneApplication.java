package com.littledyf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author dengyifan
 * @create 2023/11/1 17:01
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient
public class OneApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneApplication.class, args);
    }
}
