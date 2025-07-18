package com.example.springcloud.alibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "nacos-payment-provider")
public interface PaymentService {
    @GetMapping(value = "/payment/get/{id}")
    CommonResult get(@PathVariable("id") Long id);

}
