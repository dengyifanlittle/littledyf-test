package com.littledyf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author dengyifan
 * @create 2023/11/2 9:06
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwoApplication.class, args);
    }

}
