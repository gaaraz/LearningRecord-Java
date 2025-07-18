package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/7/1
 */
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@MapperScan("com.example.mapper")
public class NettyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }
}

/**

 docker run -d \
 --name redis \
 -p 6379:6379 \
 -v /Users/Gaara/develop/docker/redis/conf/redis.conf:/etc/redis/redis.conf \
 -v /Users/Gaara/develop/docker/redis/data:/data \
 -v /Users/Gaara/develop/docker/redis/log:/var/log/redis \
 redis:6.2.18  \
 redis-server /etc/redis/redis.conf


 docker run -d \
 -p 3306:3306 \
 -v /Users/Gaara/develop/mysql/mysql_conf:/etc/mysql/conf.d \
 -v /Users/Gaara/develop/mysql/mysql_data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=123456 \
 --name mysql \
 --restart=always \
 --privileged=true \
 mysql:5.7

 */

