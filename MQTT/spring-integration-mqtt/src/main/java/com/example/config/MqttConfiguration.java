package com.example.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@Configuration
public class MqttConfiguration {
    @Resource
    private MqttConfigurationProperties mqttConfigurationProperties;

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory(){
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(mqttConfigurationProperties.getUsername());
        options.setPassword(mqttConfigurationProperties.getPassword().toCharArray());
        options.setServerURIs(new String[]{mqttConfigurationProperties.getUrl()});

        factory.setConnectionOptions(options);
        return factory;
    }
}
