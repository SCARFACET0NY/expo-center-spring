package com.anton.expocenterspring.services;

import com.anton.expocenterspring.dto.TicketDto;

public interface TicketService {
    TicketDto createTicketDto(long expositionId);
}
