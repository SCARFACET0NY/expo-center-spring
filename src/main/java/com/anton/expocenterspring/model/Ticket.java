package com.anton.expocenterspring.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "quantity")
    private Integer quantity;
    @OneToOne
    private Exposition exposition;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
