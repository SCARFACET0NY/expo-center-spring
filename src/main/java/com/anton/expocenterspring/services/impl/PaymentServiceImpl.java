package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.auth.UserPrincipal;
import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.repositories.ExpositionRepository;
import com.anton.expocenterspring.repositories.PaymentRepository;
import com.anton.expocenterspring.repositories.TicketRepository;
import com.anton.expocenterspring.services.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final int ONE_TICKET = 1;
    private static final int ROWS_PER_PAGE = 10;
    private final ExpositionRepository expositionRepository;
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(ExpositionRepository expositionRepository, TicketRepository ticketRepository, PaymentRepository paymentRepository) {
        this.expositionRepository = expositionRepository;
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Ticket createTicket(long expositionId) {
        Exposition exposition = expositionRepository.findById(expositionId).get();
        LocalDate date;

        if (LocalDate.now().isAfter(exposition.getStartDate())) {
            date = LocalDate.now();
        } else {
            date = exposition.getStartDate();
        }

        return Ticket.builder().date(date).quantity(ONE_TICKET).exposition(exposition).build();
    }

    @Override
    public Payment createPayment(double total) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        return Payment.builder().date(LocalDateTime.now()).total(total).user(user).build();
    }

    @Override
    public double getCartTotal(Map<String, Ticket> cart) {
        return Math.round(cart.values().stream()
                .mapToDouble(ticket -> ticket.getExposition().getPrice() * ticket.getQuantity())
                .reduce(0.0, Double::sum) * 100) / 100.0;
    }

    @Override
    @Transactional
    public void savePayment(double total, Map<String, Ticket> cart) {
        Payment payment = paymentRepository.save(createPayment(total));
        cart.values().forEach(ticket -> {
            ticket.setPayment(payment);
            ticketRepository.save(ticket);
        });
    }

    @Override
    public Page<Payment> getPaymentsPageForUser(User user, Integer pageNumber) {
        return paymentRepository.findAllByUserOrderByDateDesc(user, PageRequest.of(pageNumber, ROWS_PER_PAGE));
    }
}
