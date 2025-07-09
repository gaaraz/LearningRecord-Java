package com.example.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@Component
public class ReceiverMessageHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        MessageHeaders headers = message.getHeaders();
        Object topic = headers.get("mqtt_receivedTopic");
        System.out.println("topic: " + topic);
        System.out.println("payload: "+ message.getPayload());
    }
}
