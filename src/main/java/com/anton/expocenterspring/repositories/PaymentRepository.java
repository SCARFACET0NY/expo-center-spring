package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
