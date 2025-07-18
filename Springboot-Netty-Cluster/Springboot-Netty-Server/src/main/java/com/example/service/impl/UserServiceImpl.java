package com.example.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.example.dto.UserDto;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public void addUser(UserDto user) {
        userMapper.insert(user);
    }

    @Override
    public UserDto queryUser(String username) {
        UserDto param = new UserDto();
        param.setUsername(username);
        UserDto userDto = userMapper.selectOne(param);
        return userDto;
    }

    @Override
    public Boolean login(String username, String password) {
        UserDto param = new UserDto();
        param.setUsername(username);
        UserDto userDto = userMapper.selectOne(param);
        if (userDto == null ) {
            return false;
        }
        String pwd = DigestUtil.md5Hex(password);
        return userDto.getPassword().equals(pwd);
    }
}
