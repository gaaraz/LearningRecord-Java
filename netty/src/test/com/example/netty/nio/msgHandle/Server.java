package com.example.netty.nio.msgHandle;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import static com.example.netty.ByteBufferUtil.debugAll;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/12/4
 */
@Slf4j
public class Server {
    private static void split(ByteBuffer source){
        source.flip();
        for (int i = 0; i < source.limit(); i++){
            if (source.get(i) == '\n'){
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j<length; j++){
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("sscKey: {}", sscKey);
        ssc.bind(new InetSocketAddress(8888));

        while (true){
            selector.select();

            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                log.debug("SelectionKey: {}", key);
                iter.remove();

                if (key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    // 将ByteBuffer作为附件关联到SelectionKey
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("SocketChannel sc : {}", sc);
                }else if (key.isReadable()){
                    try {
                        SocketChannel channel = (SocketChannel)key.channel();
                        // 获取SelectionKey上的附件
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        int read = channel.read(buffer);
                        // -1代表客户端断开事件
                        if (read == -1){
                            // 取消注册
                            log.debug("客户端已正常断开连接");
                            key.cancel();
                        } else {
                            split(buffer);
                            if (buffer.position() == buffer.limit()){
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }
                        }
                    }catch (IOException e){
                        // 客户端意外断开
                        e.printStackTrace();
                        key.cancel();
                    }
                }
            }
        }
    }
}
