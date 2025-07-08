package com.example.netty.client;

import com.example.netty.client.handler.RpcResponseMessageHandler;
import com.example.netty.message.RpcRequestMessage;
import com.example.netty.protocol.MessageCodecSharable;
import com.example.netty.protocol.ProtocolFrameDecoder;
import com.example.netty.protocol.SequenceIdGenerator;
import com.example.netty.server.service.UserService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/8
 */
@Slf4j
public class RpcClientManager {
    public static void main(String[] args) {
        UserService userService = getProxyService(UserService.class);
        System.out.println(userService.sayHello("李四"));
    }

    // 获取代理对象
    public static <T> T getProxyService(Class<T> serviceClass){
        ClassLoader loader = serviceClass.getClassLoader();
        Class<?>[] interfaces = {serviceClass};

        Object o = Proxy.newProxyInstance(loader, interfaces, (proxy, method, args) -> {
            // 1. 将方法调用转换为 消息对象
            int sequenceId = SequenceIdGenerator.nextId();
            RpcRequestMessage msg = new RpcRequestMessage(
                    sequenceId,
                    serviceClass.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
            );

            // 2. 发送消息对象
            getChannel().writeAndFlush(msg);
            // 3. 准备一个空 Promise 对象，来接收结果             指定 promise 对象异步接收结果线程
            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler.PROMISE.put(sequenceId, promise);

            promise.await();
            if (promise.isSuccess()) {
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
        return (T)o;
    }

    private static Channel channel = null;
    private static final Object LOCK = new Object();

    public static Channel getChannel(){
        if (channel != null){
            return channel;
        }

        synchronized (LOCK){
            if (channel != null){
                return channel;
            }
            initChannel();
            return channel;
        }
    }

    private static void initChannel(){
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_HANDLER = new RpcResponseMessageHandler();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new ProtocolFrameDecoder());
                sc.pipeline().addLast(LOGGING_HANDLER);
                sc.pipeline().addLast(MESSAGE_CODEC);
                sc.pipeline().addLast(RPC_HANDLER);
            }
        });

        try {
            channel = bootstrap.connect("localhost", 8080).sync().channel();
            channel.closeFuture().addListener(future ->{
               group.shutdownGracefully();
            });
        }catch (Exception e){
            log.error("client error:", e);
        }
    }
}
