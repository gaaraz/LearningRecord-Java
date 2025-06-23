package com.example.netty.netty.promise;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/10
 */
@Slf4j
public class NettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.准备eventLoop对象
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // 2.可以主动创建promise,结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);
        new Thread(() ->{
            log.debug("calculate...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 3.设置结果
            promise.setSuccess(666);
        }).start();

        // 4.获取结果
        log.debug("wait result...");
//        log.debug("result:{}", promise.get());

        promise.addListener(future ->{
            log.debug("result:{}", promise.getNow());
            eventLoop.shutdownGracefully();
        });
    }
}
