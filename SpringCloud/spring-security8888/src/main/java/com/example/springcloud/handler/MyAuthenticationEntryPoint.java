package com.example.springcloud.handler;

import com.alibaba.fastjson.JSON;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CommonResult result = CommonResult.error(HttpStatus.UNAUTHORIZED.value(), "=====认证失败请重新登录=====");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
