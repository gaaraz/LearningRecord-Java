package com.example;

import com.example.gateway.MqttMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/9
 */
@SpringBootTest(classes = MqttDemoApplication.class)
@RunWith(SpringRunner.class)
public class MqttTest {
    @Resource
    private MqttMessageSender mqttMessageSender;

    @Test
    public void test(){
        mqttMessageSender.sendToMqtt("topic/java/d", "hello mqtt");
    }
}
