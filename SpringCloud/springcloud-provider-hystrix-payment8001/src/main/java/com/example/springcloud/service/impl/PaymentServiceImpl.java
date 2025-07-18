package com.example.springcloud.service.impl;

import com.example.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfoSuccess(Integer id) {
        return "线程名:" + Thread.currentThread().getName() + ",paymentInfoSuccess, id: " + id;
    }

//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @Override
    public String paymentInfoTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名:" + Thread.currentThread().getName() + ",paymentInfoTimeout, id: " + id;
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "===== 线程名:" + Thread.currentThread().getName() + ",paymentInfoTimeoutHandler, id: " + id + "======";
    }
}
