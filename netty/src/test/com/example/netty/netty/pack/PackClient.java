package com.example.netty.netty.pack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/14
 */
@Slf4j
public class PackClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            log.debug("connected...");
                            sc.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    log.debug("sending...");
//                                    Random random = new Random();
//                                    char c = 'a';
                                    for (int i = 0; i < 100; i++) {
                                        ByteBuf buf = ctx.alloc().buffer(10);
                                        buf.writeBytes(new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
                                        ctx.writeAndFlush(buf);
                                    }
//                                    ctx.writeAndFlush(buf);
                                    log.debug("sended...");
                                }
                            });
                        }
                    })
                    .connect("127.0.0.1", 8989).sync()
                    .channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            log.debug("finish...");
            group.shutdownGracefully();
        }
    }
}
