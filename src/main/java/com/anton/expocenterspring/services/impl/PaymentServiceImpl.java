package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.repositories.PaymentRepository;
import com.anton.expocenterspring.services.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final int ROWS_PER_PAGE = 10;
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

    @Override
    public Page<Payment> getPaymentsPageForUser(User user, Integer pageNumber) {
        return paymentRepository.findAllByUserOrderByDateDesc(user, PageRequest.of(pageNumber, ROWS_PER_PAGE));
    }
}
