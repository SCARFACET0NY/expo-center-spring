package com.anton.expocenterspring.services;

import com.anton.expocenterspring.dto.TicketDto;

import java.util.Map;

public interface TicketService {
    TicketDto createTicketDto(long expositionId);

    double getCartTotal(Map<String, TicketDto> cart);
}
