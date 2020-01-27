package com.anton.expocenterspring.services;

import com.anton.expocenterspring.dto.TicketDto;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.Ticket;

import java.util.Map;

public interface TicketService {
    TicketDto createTicketDto(long expositionId);

    void saveTicket(Ticket ticket, Payment payment);

    double getCartTotal(Map<String, TicketDto> cart);
}
