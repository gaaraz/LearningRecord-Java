package com.example.springcloud.alibaba.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.springcloud.entities.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl {
    @SentinelResource("test")
    public CommonResult test(){
        return CommonResult.success("test.....success");
    }
}
