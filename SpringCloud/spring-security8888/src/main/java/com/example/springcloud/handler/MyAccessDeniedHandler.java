package com.example.springcloud.handler;

import com.alibaba.fastjson.JSON;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CommonResult result = CommonResult.error(HttpStatus.FORBIDDEN.value(), "=====权限不足=====");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
