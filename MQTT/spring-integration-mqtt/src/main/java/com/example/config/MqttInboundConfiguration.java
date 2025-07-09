package com.example.config;

import com.example.handler.ReceiverMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@Configuration
public class MqttInboundConfiguration {
    @Resource
    private MqttConfigurationProperties mqttConfigurationProperties;

    @Resource
    private ReceiverMessageHandler receiverMessageHandler;

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer messageProducer(MqttPahoClientFactory mqttPahoClientFactory){
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        mqttConfigurationProperties.getUrl(),
                        mqttConfigurationProperties.getSubClientId(),
                        mqttPahoClientFactory,
                        mqttConfigurationProperties.getSubTopic().split(",")
                );
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler messageHandler(){
        return this.receiverMessageHandler;
    }
}
