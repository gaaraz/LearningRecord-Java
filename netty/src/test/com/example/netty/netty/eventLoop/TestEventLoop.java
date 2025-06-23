package com.example.netty.netty.eventLoop;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/9
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        // 1.创建事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup(2);     // io事件,普通任务,定时任务
//        DefaultEventLoopGroup group = new DefaultEventLoopGroup(2);   // 普通任务,定时任务

        // 2.获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 3.执行普通任务
//        group.next().execute(() ->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            log.debug("execute normal task");
//        });

        // 4.执行定时任务
        group.next().scheduleAtFixedRate(() ->{
            log.debug("schedule task");
        }, 0, 1, TimeUnit.SECONDS);

        log.debug("main");
    }
}
