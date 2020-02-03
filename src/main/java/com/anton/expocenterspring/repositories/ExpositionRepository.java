package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.model.Exposition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;

public interface ExpositionRepository extends CrudRepository<Exposition, Long> {
    Set<Exposition> findExpositionsByHall_IdAndEndDateGreaterThanEqual(long id, LocalDate date);

    Set<Exposition> findExpositionsByEndDateGreaterThanEqual(LocalDate date);

    @Query("SELECT e FROM Exposition e WHERE e.title LIKE %:text%")
    Set<Exposition> searchByTitle(@Param("text") String query);
}
