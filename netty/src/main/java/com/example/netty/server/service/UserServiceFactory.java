package com.example.netty.server.service;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
public abstract class UserServiceFactory {
    private static UserService userService = new UserServiceMemoryImpl();


    public static UserService getUserService(){
        return userService;
    }
}
