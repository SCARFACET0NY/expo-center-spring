package com.anton.expocenterspring.dto;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDto {
    private Ticket ticket;
    private Exposition exposition;
}
