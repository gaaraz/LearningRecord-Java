package com.example.netty.nio.multiThread;

import com.example.netty.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zzy
 * @createDate: 2024/1/2
 */
@Slf4j
public class MultiThreadWorkerQueueServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("Boss");
        final ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        Selector bossSelector = Selector.open();
        ssc.register(bossSelector, SelectionKey.OP_ACCEPT, null);

        Worker[] workers = new Worker[2];
        for (int i=0; i<workers.length; i++){
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger index = new AtomicInteger();
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
                    workers[index.getAndIncrement() % workers.length].register(sc);
                    log.debug("=====after register====={}", sc.getRemoteAddress());
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
            queue.add(() -> {
                try {
                    log.debug("=====开始worker注册逻辑=====");
                    sc.register(workerSelector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            log.debug("=====添加队列,唤醒worker=====");
            workerSelector.wakeup();
        }

        @Override
        public void run() {
            while (true){
                try {
                    log.debug("=====worker 开始阻塞=====");
                    workerSelector.select();
                    log.debug("=====worker 开始运行=====");
                    Runnable task = queue.poll();
                    if (task != null){
                        task.run();
                    }
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
