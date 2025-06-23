package com.example.netty.netty.promise;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/10
 */
@Slf4j
public class DeadLockCheck {
    public static void main(String[] args) {
        DefaultEventLoop eventLoop = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        eventLoop.submit(() ->{
            log.debug("1");
            try {
                promise.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.debug("2");
        });

        eventLoop.submit(() ->{
            log.debug("3");
            try {
                promise.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.debug("4");
        });
    }
}
