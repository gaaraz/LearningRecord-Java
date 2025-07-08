package com.example.netty.server.handler;

import com.example.netty.message.RpcRequestMessage;
import com.example.netty.message.RpcResponseMessage;
import com.example.netty.server.service.ServicesFactory;
import com.example.netty.server.service.UserService;
import com.example.netty.util.SpringContextUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/8
 */
@Slf4j
@ChannelHandler.Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) {
        RpcResponseMessage response = new RpcResponseMessage();
        response.setSequenceId(msg.getSequenceId());
        try {
            Object bean = ServicesFactory.getService(Class.forName(msg.getInterfaceName()));
            Method method = bean.getClass().getMethod(msg.getMethodName(), msg.getParameterTypes());
            Object invoke = method.invoke(bean, msg.getParameterValue());
            response.setReturnValue(invoke);
        }catch (Exception e){
            e.printStackTrace();
            response.setExceptionValue(e);
        }
        ctx.writeAndFlush(response);
    }
}

/**
 * java.lang.NoClassDefFoundError:
 * Could not initialize class com.example.netty.server.service.ServicesFactory
 */
