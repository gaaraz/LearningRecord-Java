package com.example.netty.nio.multiThread;

import com.example.netty.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/12/13
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("Boss");
        final ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        Selector bossSelector = Selector.open();
        ssc.register(bossSelector, SelectionKey.OP_ACCEPT, null);
        Worker worker = new Worker("worker-0");
        while (true){
            bossSelector.select();
            Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.debug("=====before register====={}", sc.getRemoteAddress());
                    worker.register();
                    // 问题workerSelector阻塞后,后续的SocketChannel注册不上
                    sc.register(worker.workerSelector, SelectionKey.OP_READ, null);
                }
            }
        }
    }

    static class Worker implements Runnable{
        private Thread thread;
        private Selector workerSelector;
        private String name;
        private volatile boolean start = false;

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程和selector
        public void register() throws IOException {
            if (!start){
                thread = new Thread(this, name);
                workerSelector = Selector.open();
                thread.start();
                start = true;
                log.debug("=====worker初始化结束=====");
            }
        }

        @Override
        public void run() {
            while (true){
                try {
                    log.debug("=====worker 开始阻塞=====");
                    workerSelector.select();
                    log.debug("=====worker 开始运行=====");
                    Iterator<SelectionKey> iterator = workerSelector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()){
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            int read = channel.read(buffer);
                            if (read == -1){
                                // 取消注册
                                log.debug("客户端已正常断开连接");
                                key.cancel();
                            } else {
                                buffer.flip();
                                ByteBufferUtil.debugAll(buffer);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
