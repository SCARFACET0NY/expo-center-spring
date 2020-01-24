package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.model.Exposition;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Set;

public interface ExpositionRepository extends CrudRepository<Exposition, Long> {
    Set<Exposition> findExpositionsByHall_IdAndEndDateGreaterThanEqual(long id, LocalDate date);
}
