package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/1
 */
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@MapperScan("com.example.mapper")
public class NettyServerApplication01 {
    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication01.class, args);
    }
}

