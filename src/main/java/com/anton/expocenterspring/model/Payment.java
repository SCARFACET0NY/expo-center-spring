package com.anton.expocenterspring.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    @Column(name = "total")
    private double total;
    @Column(name = "payment_date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "payment")
    private Set<Ticket> tickets;
}
