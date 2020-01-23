package com.anton.expocenterspring.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Ticket() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
