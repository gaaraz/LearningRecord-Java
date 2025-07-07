package com.example.server.redis;

import com.example.message.ChatRequestMessage;
import com.example.message.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/7
 */
@Service
public class RedisMsgPublisher {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void publish(String topic, Message message) {
        redisTemplate.convertAndSend(topic, message);
    }

    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void setHash(String key, String hashKey, String hashValue){
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    public Boolean hasKey(String key, String hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public String getHash(String key, String hashKey){
        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    public void deleteHash(String key, String hashKey){
        redisTemplate.opsForHash().delete(key, hashKey);
    }
}
