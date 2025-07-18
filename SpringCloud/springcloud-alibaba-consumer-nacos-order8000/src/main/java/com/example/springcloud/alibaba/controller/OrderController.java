package com.example.springcloud.alibaba.controller;

import com.example.springcloud.alibaba.service.PaymentService;
import com.example.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class OrderController {
    @Value("${config.info}")
    private String configInfo;

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public CommonResult payment(@PathVariable("id") Long id){
//        return paymentService.get(id);
        return info();
    }

    @GetMapping(value = "/consumer/payment/test/{id}")
    public CommonResult test(@PathVariable("id") Long id){
//        return paymentService.get(id);
        return info();
    }


    @GetMapping(value = "/consumer/payment/nacos/info")
    public CommonResult info(){
        return CommonResult.success(configInfo);
    }
}
