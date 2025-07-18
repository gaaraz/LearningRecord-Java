package com.example.springcloud.controller;

import com.example.springcloud.dto.Payment;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.add(payment);
        if (result > 0){
            return CommonResult.success("数据插入成功,port:" + serverPort, result);
        } else {
            return CommonResult.error(402, "插入失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        Payment result = paymentService.getById(id);
        if (result != null){
            return CommonResult.success("数据查询成功,port:" + serverPort, result);
        } else {
            return CommonResult.error(402, "id:" + id + "未找到相应结果");
        }
    }
}
