package com.example.springcloud.service;

import com.example.springcloud.dto.User;
import com.example.springcloud.entities.CommonResult;

public interface LoginService {
    CommonResult login(User user);
}
