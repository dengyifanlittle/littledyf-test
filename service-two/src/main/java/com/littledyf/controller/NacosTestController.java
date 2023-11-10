package com.littledyf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dengyifan
 * @create 2023/11/1 17:02
 * @description
 */

@RestController
@RequestMapping("/nacos")
public class NacosTestController {

    @GetMapping("/test")
    public String test() {

        return "8099";
    }

    @GetMapping("/test-one")
    public String testOne() {

        return "8099-one";
    }


    @GetMapping("/test-two")
    public String testTwo() {

        return "8099-two";
    }
}
