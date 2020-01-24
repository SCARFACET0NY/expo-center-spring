package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.model.Exposition;
import org.springframework.data.repository.CrudRepository;

public interface ExpositionRepository extends CrudRepository<Exposition, Long> {
}