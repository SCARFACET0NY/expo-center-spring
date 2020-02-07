package com.anton.expocenterspring.services;

import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.model.User;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PaymentService {
    Ticket createTicket(long expositionId);

    Payment createPayment(double total);

    double getCartTotal(Map<String, Ticket> cart);

    void savePayment(double total, Map<String, Ticket> cart);

    Page<Payment> getPaymentsPageForUser(User user, Integer pageNumber);
}
