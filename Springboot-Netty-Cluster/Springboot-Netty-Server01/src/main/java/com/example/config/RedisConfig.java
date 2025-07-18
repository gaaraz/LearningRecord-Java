package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/6
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory connectionFactory;

    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer(Object.class));
        return redisTemplate;
    }

//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(){
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageListenerAdapter(){
//        return new MessageListenerAdapter(new RedisMsgSubscriber());
//    }
}
