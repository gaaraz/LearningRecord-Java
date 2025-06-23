package com.example.netty.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/17
 */
@Slf4j
public class RedisProtocol {
    public static void main(String[] args) throws InterruptedException {
        byte[] LINE = {13, 10};     // 回车,换行
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        sc.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                set(ctx);
                                get(ctx);
                            }
                            private void get(ChannelHandlerContext ctx){
                                ByteBuf buf = ctx.alloc().buffer();
                                buf.writeBytes("*2".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$3".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("get".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$4".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("name".getBytes());
                                buf.writeBytes(LINE);
                                ctx.writeAndFlush(buf);
                            }
                            private void set(ChannelHandlerContext ctx){
                                ByteBuf buf = ctx.alloc().buffer();
                                buf.writeBytes("*3".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$3".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("set".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$4".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("name".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$4".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("lisi".getBytes());
                                buf.writeBytes(LINE);
                                ctx.writeAndFlush(buf);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(Charset.forName("UTF-8")));
                            }
                        });
                    }
                })
                .connect("127.0.0.1", 6379).sync()
                .channel().closeFuture().sync();
    }
}
