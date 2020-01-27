package com.anton.expocenterspring.services;

import com.anton.expocenterspring.model.Payment;

public interface PaymentService {
    Payment createPayment(double total);

    Payment savePayment(double total);
}
