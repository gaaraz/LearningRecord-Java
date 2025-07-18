package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamKafkaProviderMain5001 {
    public static void main(String[] args) {
        SpringApplication.run(StreamKafkaProviderMain5001.class, args);
    }
}
