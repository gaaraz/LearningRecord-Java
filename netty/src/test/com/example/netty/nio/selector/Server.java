package com.example.netty.nio.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/11/24
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建selector,管理多个channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);


        /**
         *  建立selector和channel的联系
         *  SelectionKey 就是将来事件发生后,通过它可以知道是什么事件和哪个channel的事件
         *  事件类型:
         *      accept: 会在有连接时触发
         *      connect:客户端,连接建立后触发
         *      read:   可读事件
         *      write:  可写事件
         */
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // 设置sscKey只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("sscKey: {}", sscKey);
        ssc.bind(new InetSocketAddress(8888));

        while (true){
            // select方法,没有事件发生,线程阻塞,有事件,线程才会恢复运行
            // select在事件未处理时,它不会阻塞,事件发生后要么处理,要么取消,不能置之不理
            selector.select();

            // 处理事件,selectedKeys内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                log.debug("SelectionKey: {}", key);
                iter.remove();

                // 区分事件类型
                if (key.isAcceptable()){        // 连接事件
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("SocketChannel sc : {}", sc);
                }else if (key.isReadable()){        // read事件
                    try {
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        int read = channel.read(buffer);
                        // -1代表客户端正常断开
                        if (read == -1){
                            // 取消注册
                            log.debug("客户端已正常断开连接");
                            key.cancel();
                        } else {
                            buffer.flip();
                            System.out.println(Charset.defaultCharset().decode(buffer));
                            buffer.clear();
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
