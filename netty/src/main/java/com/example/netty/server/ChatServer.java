package com.example.netty.server;

import com.example.netty.protocol.MessageCodecSharable;
import com.example.netty.protocol.ProtocolFrameDecoder;
import com.example.netty.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
@Slf4j
public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();

        LoginRequestMessageHandler LOGIN_HANDLER = new LoginRequestMessageHandler();
        ChatRequestMessageHandler CHAT_HANDLER = new ChatRequestMessageHandler();
        GroupCreateRequestMessageHandler GROUP_CREATE_HANDLER = new GroupCreateRequestMessageHandler();
        GroupJoinRequestMessageHandler GROUP_JOIN_HANDLER = new GroupJoinRequestMessageHandler();
        GroupMembersRequestMessageHandler GROUP_MEMBERS_HANDLER = new GroupMembersRequestMessageHandler();
        GroupQuitRequestMessageHandler GROUP_QUIT_HANDLER = new GroupQuitRequestMessageHandler();
        GroupChatRequestMessageHandler GROUP_CHAT_HANDLER = new GroupChatRequestMessageHandler();
        QuitHandler QUIT_HANDLER = new QuitHandler();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new ProtocolFrameDecoder());      // 预设长度
                            sc.pipeline().addLast(LOGGING_HANDLER);                 // 日志
                            sc.pipeline().addLast(MESSAGE_CODEC);                   // 编解码器类
                            // 用来判断是不是读空闲时间过长或写空闲时间过长
                            // 5S内如果没有收到channel的数据,会触发一个IdleState#READER_IDLE事件
//                            sc.pipeline().addLast(new IdleStateHandler(5, 0, 0));
//                            sc.pipeline().addLast(new ChannelDuplexHandler(){
//                                @Override
//                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//                                    IdleStateEvent event = (IdleStateEvent) evt;
//                                    // 触发读空闲事件
//                                    if (event.state() == IdleState.READER_IDLE) {
//                                        log.debug("长时间未收到客户端的消息,关闭连接");
//                                        ctx.channel().close();
//                                    }
//                                }
//                            });

                            sc.pipeline().addLast(LOGIN_HANDLER);
                            sc.pipeline().addLast(CHAT_HANDLER);
                            sc.pipeline().addLast(GROUP_CREATE_HANDLER);
                            sc.pipeline().addLast(GROUP_JOIN_HANDLER);
                            sc.pipeline().addLast(GROUP_MEMBERS_HANDLER);
                            sc.pipeline().addLast(GROUP_QUIT_HANDLER);
                            sc.pipeline().addLast(GROUP_CHAT_HANDLER);
                            sc.pipeline().addLast(QUIT_HANDLER);
                        }
                    });
            Channel channel = serverBootstrap.bind(8888).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
