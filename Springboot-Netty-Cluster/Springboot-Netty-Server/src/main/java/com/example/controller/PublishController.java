package com.example.controller;

import com.example.server.redis.RedisMsgPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/7
 */
@RestController
@RequestMapping("/redis")
public class PublishController {
    @Resource
    private RedisMsgPublisher publisher;

    @GetMapping("/publish")
    public String publish(){
//        publisher.publish("chat", "hello");
        publisher.set("name", "zzy");
        return "SUCCESS";
    }
}
