package com.example.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@Component
@RequiredArgsConstructor
public class MqttMessageSender {
    private final MqttGateway mqttGateway;

    public void sendToMqtt(String topic, String message) {
        mqttGateway.sendToMqtt(topic, message);
    }

    public void sendToMqtt(String topic, int qos, String message) {
        mqttGateway.sendToMqtt(topic, qos, message);
    }
}
