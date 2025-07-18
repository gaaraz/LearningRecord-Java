package com.example.server.handler;

import com.example.server.redis.RedisMsgPublisher;
import com.example.server.session.SessionFactory;
import com.example.util.SpringContextUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@Slf4j
@ChannelHandler.Sharable
public class QuitHandler extends ChannelInboundHandlerAdapter {
    // 当连接断开时触发inactive事件
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        RedisMsgPublisher redisMsg = SpringContextUtil.getBean(RedisMsgPublisher.class);
        String name = SessionFactory.getSession().unbind(ctx.channel());
        redisMsg.deleteHash("chat:cache:login", name);
        log.debug("{} 离线了", ctx.channel());
    }

    // 当出现异常时触发
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("{} 异常断开,异常:{}", ctx.channel(), cause.getCause().getMessage());
    }
}
