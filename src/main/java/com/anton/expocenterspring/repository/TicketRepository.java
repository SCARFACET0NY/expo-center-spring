package com.anton.expocenterspring.repository;

import com.anton.expocenterspring.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
