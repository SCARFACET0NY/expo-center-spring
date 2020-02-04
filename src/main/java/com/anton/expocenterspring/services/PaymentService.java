package com.anton.expocenterspring.services;

import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.User;
import org.springframework.data.domain.Page;

public interface PaymentService {
    Payment createPayment(double total);

    Payment savePayment(double total);

    Page<Payment> getPaymentsPageForUser(User user, Integer pageNumber);
}
