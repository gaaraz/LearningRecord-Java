package com.example.netty.nio.unblock;

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
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        List<SocketChannel> channels = new ArrayList<>();
        while (true){
//            log.debug("connecting...");
            SocketChannel sc = ssc.accept();
            if (sc != null){
                sc.configureBlocking(false);
                log.debug("connected...{}", sc);
                channels.add(sc);
            }

            for (SocketChannel channel : channels){
//                log.debug("before read...{}", channel);
                int read = channel.read(buffer);
                if (read > 0){
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.debug("after read...{}", channel);
                }
            }
        }
    }
}
