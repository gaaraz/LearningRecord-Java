package com.example;

import com.example.config.MqttConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@SpringBootApplication
@EnableConfigurationProperties(value = MqttConfigurationProperties.class)
public class MqttDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttDemoApplication.class, args);
    }
}
