package com.example.netty.netty.pack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @description: 服务端固定长度解决黏包
 * @author: zzy
 * @createDate: 2025/6/14
 */
@Slf4j
public class PackClient2 {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            log.debug("connected...");
                            sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            sc.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    log.debug("sending...");
                                    Random r = new Random();
                                    char c = 'a';
                                    ByteBuf buf = ctx.alloc().buffer();
                                    for (int i = 0; i < 10; i++) {
                                        byte[] bytes = fillBytes(c, r.nextInt(10) + 1);
                                        c++;
                                        buf.writeBytes(bytes);
                                    }
                                    ctx.writeAndFlush(buf);

                                    log.debug("sended...");
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8989).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            log.debug("finish...");
            group.shutdownGracefully();
        }
    }

    public static byte[] fillBytes(char c, int size) {
        byte[] bytes = new byte[10];
        for (int i = 0; i < 10; i++) {
            if (i < size){
                bytes[i] = (byte) c;
            } else {
                bytes[i] = '_';
            }
        }
        return bytes;
    }
}
