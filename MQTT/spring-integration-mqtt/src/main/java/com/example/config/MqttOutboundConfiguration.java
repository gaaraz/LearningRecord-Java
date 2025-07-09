package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@Configuration
public class MqttOutboundConfiguration {
    @Resource
    private MqttConfigurationProperties mqttConfigurationProperties;

    @Resource
    private MqttPahoClientFactory mqttPahoClientFactory;

    @Bean
    public MessageChannel mqttOutputChannel(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler mqttOutboundMessageHandler(){
        MqttPahoMessageHandler handler =
                new MqttPahoMessageHandler(
                        mqttConfigurationProperties.getUrl(),
                        mqttConfigurationProperties.getPubClientId(),
                        mqttPahoClientFactory);
        handler.setAsync(true);
        handler.setDefaultQos(1);
        handler.setDefaultTopic("default");
        return handler;
    }
}
