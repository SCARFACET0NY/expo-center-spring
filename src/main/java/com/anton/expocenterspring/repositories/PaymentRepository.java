package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {
    Page<Payment> findAllByUserOrderByDateDesc(User user, Pageable pageable);
}
