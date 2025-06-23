package com.example.netty.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/7
 */
public class HelloServer {
    public static void main(String[] args) {
        // 1.启动器,组装netty组件,启动服务
        new ServerBootstrap()
                // 2.group组(selector,thread)
                .group(new NioEventLoopGroup())
                // 3.选择服务器的NioServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4.确定child需要执行哪些操作
                .childHandler(
                        // 5.chanel和客户端进行数据读写的通道 Initializer初始化
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nSC) throws Exception {
                                // 6. 添加具体的handler
                                nSC.pipeline().addLast(new StringDecoder());    // 将ByteBuf转化为字符串
                                nSC.pipeline().addLast(new ChannelInboundHandlerAdapter(){  // 自定义handler
                                    @Override   // 读事件
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                })
                // 7. 绑定监听端口
                .bind(8899);
    }
}
