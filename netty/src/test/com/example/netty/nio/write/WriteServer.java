package com.example.netty.nio.write;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/12/5
 */
@Slf4j
public class WriteServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        int count = 0;
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);

                    // 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i=0;i<5000000;i++){
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
//                    while (buffer.hasRemaining()){
//                        int write = sc.write(buffer);
//                        System.out.println(write);
//                        count += write;
//                    }
                    // 返回值代表实际写入的字节数
                    int write = sc.write(buffer);
                    log.debug("实际写入字节数:{}", write);
                    // 判断buffer是否有剩余内容
                    if (buffer.hasRemaining()){
                        // 关注可写事件
                        scKey.interestOps(scKey.interestOps()+SelectionKey.OP_WRITE);
                        // 未写完的数据挂到scKey上
                        scKey.attach(buffer);
                    }
                } else if (key.isWritable()){
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    log.debug("实际写入字节数:{}", write);
                    count += write;
                    // 清理
                    if (!buffer.hasRemaining()){
                        key.attach(null);   // 清除挂载的buffer
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE); //不关注可写事件
                    }
                }
            }
        }
    }
}
