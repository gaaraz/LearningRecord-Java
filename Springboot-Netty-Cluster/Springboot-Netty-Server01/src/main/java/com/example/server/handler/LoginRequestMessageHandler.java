package com.example.server.handler;


import com.example.message.ChatRequestMessage;
import com.example.message.LoginRequestMessage;
import com.example.message.LoginResponseMessage;
import com.example.server.redis.RedisMsgPublisher;
import com.example.server.session.SessionFactory;
import com.example.service.UserService;
import com.example.util.SpringContextUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        RedisMsgPublisher redisMsg = SpringContextUtil.getBean(RedisMsgPublisher.class);
        String username = msg.getUsername();
        String password = msg.getPassword();
        String usernameCache = redisMsg.getHash("chat:cache:login" , username);
        LoginResponseMessage response;
        if (StringUtils.isEmpty(usernameCache)){
            UserService userService = SpringContextUtil.getBean(UserService.class);
            boolean login = userService.login(username, password);
            if (login) {
                SessionFactory.getSession().bind(ctx.channel(), username);
                redisMsg.setHash("chat:cache:login", username, ctx.channel().id().toString());
                response = new LoginResponseMessage(true, "登录成功");
            } else {
                response = new LoginResponseMessage(false, "用户名或密码不正确");
            }
        } else {
            redisMsg.publish("chat:msg:login", msg);
            response = new LoginResponseMessage(false, "账号已经登陆");
        }
        ctx.writeAndFlush(response);
    }
}
