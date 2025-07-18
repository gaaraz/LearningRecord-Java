package com.example.service;

import com.example.dto.UserDto;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
public interface UserService {

    void addUser(UserDto user);

    UserDto queryUser(String username);

    Boolean login(String username, String password);
}
