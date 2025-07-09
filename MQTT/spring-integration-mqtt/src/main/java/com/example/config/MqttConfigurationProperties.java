package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@Data
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttConfigurationProperties {
    private String url;
    private String username;
    private String password;
    private String subClientId;
    private String subTopic;
    private String pubClientId;
}
