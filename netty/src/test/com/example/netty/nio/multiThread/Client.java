package com.example.netty.nio.multiThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/12/13
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();

        // 指定要连接的 服务器 和 端口号
        sc.connect(new InetSocketAddress("localhost", 8888));

        // 向 服务端 写数据
        sc.write(Charset.defaultCharset().encode("0123456789abcdef"));

        // 等待控制台输入
        System.in.read(); // 阻塞方法
    }
}
