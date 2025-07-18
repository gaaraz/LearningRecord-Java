package com.example.springcloud.controller;

import com.example.springcloud.dto.User;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SecurityController {
    @Resource
    private LoginService loginService;

    @GetMapping("/hello")
    public CommonResult hello(){
        return CommonResult.success("hello");
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody User user){
        return loginService.login(user);
    }
}
