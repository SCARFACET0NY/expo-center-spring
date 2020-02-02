package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.repositories.ExpositionRepository;
import com.anton.expocenterspring.services.ExpositionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ExpositionServiceImpl implements ExpositionService {
    private final ExpositionRepository expositionRepository;

    public ExpositionServiceImpl(ExpositionRepository expositionRepository) {
        this.expositionRepository = expositionRepository;
    }

    @Override
    public Set<Exposition> getActiveExpositionsForHall(Hall hall) {
        return expositionRepository.findExpositionsByHall_IdAndEndDateGreaterThanEqual(hall.getId(), LocalDate.now());
    }

    @Override
    public Set<Exposition> searchExpositionsByTitle(String query) {
        return expositionRepository.searchByTitle(query);
    }
}
