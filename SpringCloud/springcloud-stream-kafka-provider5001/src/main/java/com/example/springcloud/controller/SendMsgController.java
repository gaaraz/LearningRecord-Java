package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.producer.IMessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMsgController {
    @Resource
    private IMessageProducer messageProducer;

    @GetMapping("/send")
    public CommonResult send(){
        messageProducer.send();
        return CommonResult.success("success");
    }
}
