package com.example.springcloud.service.impl;

import com.example.springcloud.vo.LoginUserDetails;
import com.example.springcloud.dto.User;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.service.LoginService;
import com.example.springcloud.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;


    @Override
    public CommonResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("=====用户名或密码错误=====");
        }

        LoginUserDetails loginUser = (LoginUserDetails) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return CommonResult.success("登录成功", map);
    }
}
