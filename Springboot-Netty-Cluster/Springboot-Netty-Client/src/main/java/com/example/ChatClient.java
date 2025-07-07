package com.example;


import com.example.message.*;
import com.example.protocol.MessageCodecSharable;
import com.example.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@Slf4j
public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        CountDownLatch SIGNAL = new CountDownLatch(1);
        AtomicBoolean LOGIN = new AtomicBoolean(false);
        AtomicBoolean EXIT = new AtomicBoolean(false);
        Scanner scanner = new Scanner(System.in);

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new ProtocolFrameDecoder());
//                            sc.pipeline().addLast(LOGGING_HANDLER);
                            sc.pipeline().addLast(MESSAGE_CODEC);
                            // 用来判断是不是读写空闲时间过长
                            // 3s内如果没有向服务器写数据,会触发一个IdleState#WRITE_IDLE事件
                            sc.pipeline().addLast(new IdleStateHandler(0,3,0));
                            // ChannelDuplexHandler可以同时作为入站和出站处理器
                            sc.pipeline().addLast(new ChannelDuplexHandler(){
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event = (IdleStateEvent) evt;
                                    // 触发写空闲事件
                                    if(event.state() == IdleState.WRITER_IDLE){
                                        ctx.writeAndFlush(new PingMessage());
                                    }
                                }
                            });
                            sc.pipeline().addLast("client handler", new ChannelInboundHandlerAdapter() {
                                // 接收响应消息
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    log.debug("msg:{}", msg);
                                    if (msg instanceof LoginResponseMessage){
                                        LoginResponseMessage response = (LoginResponseMessage) msg;
                                        if (response.isSuccess()){
                                            // 登录成功
                                            LOGIN.set(true);
                                        }
                                        // 唤醒system-in线程
                                        SIGNAL.countDown();
                                    }
                                }

                                // 连接建立后触发active事件
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    // 接收用户在控制台的输入,负责想服务器发送各种消息
                                    new Thread(() -> {
                                        System.out.println("请输入用户名:");
                                        String username = scanner.nextLine();
                                        if (EXIT.get()){
                                            return;
                                        }
                                        System.out.println("请输入密码: ");
                                        String password = scanner.nextLine();
                                        if (EXIT.get()){
                                            return;
                                        }

                                        LoginRequestMessage request = new LoginRequestMessage(username, password);
                                        // 发送消息
                                        ctx.writeAndFlush(request);

                                        log.debug("======= 等待服务端响应...========");
                                        try {
                                            SIGNAL.await();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        // 登录失败
                                        if (!LOGIN.get()){
                                            ctx.channel().close();
                                            return;
                                        }

                                        while (true){
                                            System.out.println("====================================");
                                            System.out.println("send [username] [content]");
                                            System.out.println("gsend [group name] [content]");
                                            System.out.println("gcreate [group name] [m1,m2,m3...]");
                                            System.out.println("gmembers [group name]");
                                            System.out.println("gjoin [group name]");
                                            System.out.println("gquit [group name]");
                                            System.out.println("quit");
                                            System.out.println("====================================");
                                            String command = scanner.nextLine();
                                            if (EXIT.get()){
                                                return;
                                            }
                                            String[] split = command.split(" ");
                                            switch (split[0]){
                                                case "send":
                                                    ctx.writeAndFlush(new ChatRequestMessage(username, split[1], split[2]));
                                                    break;
                                                case "gsend":
                                                    ctx.writeAndFlush(new GroupChatRequestMessage(split[1], username, split[2]));
                                                    break;
                                                case "gcreate":
                                                    HashSet<String> set = new HashSet<>(Arrays.asList(split[2].split(",")));
                                                    set.add(username);
                                                    ctx.writeAndFlush(new GroupCreateRequestMessage(split[1], set));
                                                    break;
                                                case "gmembers":
                                                    ctx.writeAndFlush(new GroupMembersRequestMessage(split[1]));
                                                    break;
                                                case "gjoin":
                                                    ctx.writeAndFlush(new GroupJoinRequestMessage(split[1], username));
                                                    break;
                                                case "gquit":
                                                    ctx.writeAndFlush(new GroupQuitRequestMessage(split[1], username));
                                                    break;
                                                case "quit":
                                                    ctx.channel().close();
                                                    return;
                                            }
                                        }
                                    }, "system-in").start();
                                }

                                // 连接断开时触发
                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    log.debug("连接已经断开,按任意键退出...");
                                    EXIT.set(true);
                                }

                                // 出现异常时触发
                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    log.debug("连接已经断开,按任意键退出...");
                                    EXIT.set(true);
                                }
                            });
                        }
                    });
            Channel channel = bootstrap.connect("127.0.0.1", 8889).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.debug("client error", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
