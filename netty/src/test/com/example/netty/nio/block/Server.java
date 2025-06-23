package com.example.netty.nio.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.example.netty.ByteBufferUtil.debugRead;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/11/21
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定监听端口
        ssc.bind(new InetSocketAddress(8888));

        // 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            log.debug("connecting...");
            // accept建立客户端连接,SocketChannel用来与客户端之间通信
            SocketChannel sc = ssc.accept();    // 阻塞方法
            log.debug("connected...{}", sc);
            channels.add(sc);
            for (SocketChannel channel : channels){
                log.debug("before read...{}", channel);
                // 接收客户端发送的数据
                channel.read(buffer);           // 阻塞方法
                buffer.flip();
                debugRead(buffer);
                buffer.clear();
                log.debug("after read...{}", channel);
            }
        }
    }
}
