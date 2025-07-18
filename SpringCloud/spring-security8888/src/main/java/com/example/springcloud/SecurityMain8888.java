package com.example.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.example.springcloud.mapper")
public class SecurityMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(SecurityMain8888.class, args);
    }
}
