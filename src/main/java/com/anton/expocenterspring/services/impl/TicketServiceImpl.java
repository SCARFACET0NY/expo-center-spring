package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.repositories.ExpositionRepository;
import com.anton.expocenterspring.repositories.TicketRepository;
import com.anton.expocenterspring.services.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    public static final int ONE_TICKET = 1;
    private final ExpositionRepository expositionRepository;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(ExpositionRepository expositionRepository, TicketRepository ticketRepository) {
        this.expositionRepository = expositionRepository;
        this.ticketRepository = ticketRepository;
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
    public void saveTicket(Ticket ticket, Payment payment) {
        ticket.setPayment(payment);
        ticketRepository.save(ticket);
    }

    @Override
    public double getCartTotal(Map<String, Ticket> cart) {
        return Math.round(cart.values().stream()
                .mapToDouble(ticket -> ticket.getExposition().getPrice() * ticket.getQuantity())
                .reduce(0.0, Double::sum) * 100) / 100.0;
    }
}
