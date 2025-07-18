package com.example.springcloud.service.impl;

import com.example.springcloud.dto.Payment;
import com.example.springcloud.mapper.PaymentMapper;
import com.example.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int add(Payment payment) {
        return paymentMapper.insert(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentMapper.selectById(id);
    }
}
