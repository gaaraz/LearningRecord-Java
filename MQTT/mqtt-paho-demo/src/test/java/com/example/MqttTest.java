package com.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/8
 */

public class MqttTest {
    @Test
    public void createConnection() throws MqttException {
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "mqtt_java_client_01";

        MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
//        MqttConnectOptions options = new MqttConnectOptions();
        client.connect();

        while (true);
    }

    @Test
    public void sendMessage() throws MqttException {
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "mqtt_java_client_01";
        String username = "lisi";
        String password = "123";

        MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setCleanSession(true);
        client.connect(options);

        String connect = "hello";
        MqttMessage message = new MqttMessage(connect.getBytes());
        message.setQos(2);
        message.setRetained(true);

        client.publish("topic/java/a", message);

        client.disconnect();
        client.close();
    }

    @Test
    public void receiveMessage() throws MqttException {
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "mqtt_java_client_01";
        String username = "lisi";
        String password = "123";

        MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        client.setCallback(new MqttCallback(){
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("connectionLost:"+throwable.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage msg) throws Exception {
                System.out.println("topic:" + topic);
                System.out.println("message:" + new String(msg.getPayload()));
                System.out.println("qos:" + msg.getQos());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("deliveryComplete:"+iMqttDeliveryToken.isComplete());
            }
        });

        client.connect(options);
        client.subscribe("topic/java/b");

        while (true);
    }
}
