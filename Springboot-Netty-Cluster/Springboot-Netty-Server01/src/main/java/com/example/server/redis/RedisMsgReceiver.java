package com.example.server.redis;

import com.example.message.*;
import com.example.server.session.SessionFactory;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/7
 */
@Slf4j
@Component
public class RedisMsgReceiver {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void handleMessage(Message message, String topic) {
        Channel channel;
        switch (topic){
            case "chat:msg:login":
                LoginRequestMessage loginRequestMessage = (LoginRequestMessage) message;
                channel = SessionFactory.getSession().getChannel(loginRequestMessage.getUsername());
                channel.writeAndFlush(new LoginResponseMessage(false, "账号已经登陆"));
                break;
            case "chat:msg:handler":
                ChatRequestMessage chatRequestMessage = (ChatRequestMessage) message;
                channel = SessionFactory.getSession().getChannel(chatRequestMessage.getTo());
                channel.writeAndFlush(new ChatResponseMessage(chatRequestMessage.getFrom(), chatRequestMessage.getContent()));
                break;
        }
        log.info("Received message from Redis: {}", message);
    }
}
