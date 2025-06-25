package com.example.netty.server.service;

/**
 * @description: 用户管理接口
 * @author: zzy
 * @createDate: 2025/6/24
 */
public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
