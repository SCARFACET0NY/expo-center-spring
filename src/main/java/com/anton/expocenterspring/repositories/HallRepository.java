package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Hall;
import org.springframework.data.repository.CrudRepository;

public interface HallRepository extends CrudRepository<Hall, Long> {
    Hall findHallByType(HallType type);
}
