package com.example.config;

import com.example.server.redis.RedisMsgReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/7
 */
@Configuration
public class RedisReceiverConfig {
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory,
            MessageListenerAdapter listenerAdapter)
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        List<PatternTopic> topics = Arrays.asList(
                PatternTopic.of("chat:msg:login"),
                PatternTopic.of("chat:msg:handler")
        );
        container.addMessageListener(listenerAdapter, topics);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisMsgReceiver redisMsgReceiver){
        MessageListenerAdapter adapter = new MessageListenerAdapter(redisMsgReceiver);
        adapter.setSerializer(new FastJson2JsonRedisSerializer(Object.class));
        return adapter;
    }
}
