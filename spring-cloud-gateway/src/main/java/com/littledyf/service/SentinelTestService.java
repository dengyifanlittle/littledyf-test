package com.littledyf.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @Author dengyifan
 * @create 2023/11/9 10:42
 * @description
 */
@Service
public class SentinelTestService {
    @SentinelResource("sentinel")
    public String testSentinel() {
        return "test sentinel";
    }
}
