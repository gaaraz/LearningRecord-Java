package com.example.springcloud.producer.impl;

import com.example.springcloud.producer.IMessageProducer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageProducer implements IMessageProducer{
    private final StreamBridge streamBridge;

    public MessageProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void send() {
        String serial = UUID.randomUUID().toString();
        streamBridge.send("kafka-out-0", MessageBuilder.withPayload(serial).build());
        System.out.println("发送消息,serial" + serial);
    }
}
