package com.example.server;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.example.protocol.MessageCodecSharable;
import com.example.protocol.ProtocolFrameDecoder;
import com.example.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Properties;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
@Component
@Slf4j
public class ChatServer implements CommandLineRunner {

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Value("${server.netty.port}")
    private Integer nettyPort;
    @Value("${server.netty.application.name}")
    private String nettyName;

    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup worker = new NioEventLoopGroup();

    @Override
    public void run(String... args) throws Exception {
        this.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            try {
                this.destroy();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }));
    }

    @Async
    public void start() {

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
                            sc.pipeline().addLast(new IdleStateHandler(5, 0, 0));
                            sc.pipeline().addLast(new ChannelDuplexHandler(){
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event = (IdleStateEvent) evt;
                                    // 触发读空闲事件
                                    if (event.state() == IdleState.READER_IDLE) {
                                        log.debug("长时间未收到客户端的消息,关闭连接");
                                        ctx.channel().close();
                                    }
                                }
                            });

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
//            registerNamingService(nettyName, nettyPort);
            ChannelFuture channelFuture = serverBootstrap.bind(nettyPort).sync();
            if (channelFuture.isSuccess()) {
                log.info("Server started and listening on :{}", channelFuture.channel().localAddress());
                log.info("服务器启动成功");
            }
        } catch (InterruptedException e) {
            log.error("Server start failed:{}", e.getMessage());
        }
    }

    @PreDestroy
    public void destroy() throws InterruptedException {
        if (boss != null) {
            boss.shutdownGracefully().sync();
        }
        if (worker != null) {
            worker.shutdownGracefully().sync();
        }
        log.info("关闭Netty服务器");
    }

    private void registerNamingService(String nettyName, Integer nettyPort){
        try {
            Properties properties = new Properties();
            properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosDiscoveryProperties.getServerAddr());
            properties.setProperty(PropertyKeyConst.NAMESPACE, nacosDiscoveryProperties.getNamespace());
            properties.setProperty(PropertyKeyConst.USERNAME, nacosDiscoveryProperties.getUsername());
            properties.setProperty(PropertyKeyConst.PASSWORD, nacosDiscoveryProperties.getPassword());
            NamingService namingService = NamingFactory.createNamingService(properties);
            InetAddress address = InetAddress.getLocalHost();
            Instance instance = new Instance();
            instance.setIp(address.getHostAddress());
            instance.setPort(nettyPort);
            instance.setWeight(1.0);
            instance.setHealthy(true);
            namingService.registerInstance(nettyName, nacosDiscoveryProperties.getGroup(), instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
