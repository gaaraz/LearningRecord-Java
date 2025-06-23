package com.example.netty.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/10
 */
@Slf4j
public class JdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 创建一个线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 2. 提交任务到线程池
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("calculate...");
                Thread.sleep(1000);
                return 50;
            }
        });
        // 3.主线程通过future来获取Callable中的返回值
        log.debug("waiting...");
        log.debug("result:{}", future.get());
        service.shutdown();
    }
}
