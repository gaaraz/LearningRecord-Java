package com.example.server.handler;


import com.example.message.ChatRequestMessage;
import com.example.message.ChatResponseMessage;
import com.example.server.redis.RedisMsgPublisher;
import com.example.server.session.SessionFactory;
import com.example.util.SpringContextUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        RedisMsgPublisher redisMsg = SpringContextUtil.getBean(RedisMsgPublisher.class);
        String toCache = redisMsg.getHash("chat:cache:login", to);
        if (!StringUtils.isEmpty(toCache)){
            Channel channel = SessionFactory.getSession().getChannel(to);
            if (channel != null){
                channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
            } else {
                redisMsg.publish("chat:msg:handler", msg);
            }
        } else {
            ctx.writeAndFlush(new ChatResponseMessage(false, "用户" + to + "不在线，发送失败"));
        }
    }
}
