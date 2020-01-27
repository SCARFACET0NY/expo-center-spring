package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.repositories.PaymentRepository;
import com.anton.expocenterspring.services.PaymentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(double total) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        return Payment.builder().date(LocalDateTime.now()).total(total).user(user).build();
    }

    @Override
    public Payment savePayment(double total) {
        return paymentRepository.save(createPayment(total));
    }
}
