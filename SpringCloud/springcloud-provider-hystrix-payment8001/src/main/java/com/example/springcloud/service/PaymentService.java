package com.example.springcloud.service;

public interface PaymentService {
    String paymentInfoSuccess(Integer id);
    String paymentInfoTimeout(Integer id);
}
