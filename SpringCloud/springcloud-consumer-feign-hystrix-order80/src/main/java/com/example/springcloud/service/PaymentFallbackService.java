package com.example.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfoSuccess(Integer id) {
        return "=====paymentInfoSuccess-Fallback=====";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "=====paymentInfoTimeout-Fallback=====";
    }
}
