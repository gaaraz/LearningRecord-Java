package com.example.netty.netty.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/7
 */
@Slf4j
public class ChannelClient {
    public static void main(String[] args) throws InterruptedException {
        // 1.启动类
        ChannelFuture channelFuture = new Bootstrap()
                // 2.添加EventLoop
                .group(new NioEventLoopGroup())
                // 3.选择客户端的channel实现
                .channel(NioSocketChannel.class)
                // 4.添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override   // 在连接建立后调用
                    protected void initChannel(NioSocketChannel nSC) throws Exception {
                        nSC.pipeline().addLast(new StringEncoder());
                    }
                })
                // 5.连接到服务器
                // 异步非阻塞,main发起调用,真正执行connect的是nio线程(NioEventLoopGroup)
                .connect(new InetSocketAddress("localhost", 8989));

        // 使用sync方法同步处理结果
//        channelFuture.sync();
//        Channel channel = channelFuture.channel();
//        log.debug("{}", channel);
//
//        channel.writeAndFlush("hello, server!");

        // 使用addListener(回调对象)方法异步处理结果
        channelFuture.addListener((ChannelFutureListener) future -> {
            Channel channel = future.channel();
            log.debug("{}", channel);
            channel.writeAndFlush("hello, server!");
        });
    }
}
