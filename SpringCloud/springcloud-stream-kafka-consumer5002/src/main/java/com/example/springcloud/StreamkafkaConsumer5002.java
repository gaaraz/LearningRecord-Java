package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamkafkaConsumer5002 {
    public static void main(String[] args) {
        SpringApplication.run(StreamkafkaConsumer5002.class, args);
    }
}
