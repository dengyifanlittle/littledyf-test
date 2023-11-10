package com.littledyf.controller;

import com.littledyf.service.SentinelTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dengyifan
 * @create 2023/11/9 10:41
 * @description
 */
@RestController
@RequestMapping("/test")
public class SentinelTestController {

    @Autowired
    private SentinelTestService sentinelTestService;

    @GetMapping("/test-sentinel")
    public String testSentinel() {
        return sentinelTestService.testSentinel();
    }
}
