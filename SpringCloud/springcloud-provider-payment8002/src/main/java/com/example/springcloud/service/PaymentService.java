package com.example.springcloud.service;

import com.example.springcloud.dto.Payment;

public interface PaymentService {
    int add(Payment payment);
    Payment getById(Long id);
}
