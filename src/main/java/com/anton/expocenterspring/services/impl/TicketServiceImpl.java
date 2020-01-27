package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.dto.TicketDto;
import com.anton.expocenterspring.model.Exposition;
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
    public TicketDto createTicketDto(long expositionId) {
        TicketDto ticketDto = new TicketDto();
        Exposition exposition = expositionRepository.findById(expositionId).get();
        LocalDate date;

        if (LocalDate.now().isAfter(exposition.getStartDate())) {
            date = LocalDate.now();
        } else {
            date = exposition.getStartDate();
        }

        Ticket ticket = Ticket.builder().date(date).quantity(ONE_TICKET).exposition(exposition).build();
        ticketDto.setExposition(exposition);
        ticketDto.setTicket(ticket);

        return ticketDto;
    }

    @Override
    public double getCartTotal(Map<String, TicketDto> cart) {
        return cart.values().stream()
                .mapToDouble(item -> item.getExposition().getPrice() * item.getTicket().getQuantity())
                .reduce(0.0, Double::sum);
    }
}
