package com.anton.expocenterspring.repository;

import com.anton.expocenterspring.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
