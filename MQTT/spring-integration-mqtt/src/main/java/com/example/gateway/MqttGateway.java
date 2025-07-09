package com.example.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
public interface MqttGateway {
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
