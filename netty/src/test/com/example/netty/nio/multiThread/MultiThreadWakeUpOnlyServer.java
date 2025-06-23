package com.example.netty.nio.multiThread;

import com.example.netty.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description:
 * @author: zzy
 * @createDate: 2024/1/2
 */
@Slf4j
public class MultiThreadWakeUpOnlyServer {
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
                    worker.register(sc);
                }
            }
        }
    }

    static class Worker implements Runnable{
        private Thread thread;
        private Selector workerSelector;
        private String name;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue();
        private volatile boolean start = false;

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if (!start){
                thread = new Thread(this, name);
                workerSelector = Selector.open();
                thread.start();
                start = true;
                log.debug("=====worker初始化结束=====");
            }

            workerSelector.wakeup();
            log.debug("=====唤醒worker,开始注册socketChannel=====");
            sc.register(workerSelector, SelectionKey.OP_READ, null);
            log.debug("=====注册socketChannel结束=====");
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
