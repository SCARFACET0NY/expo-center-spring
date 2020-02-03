package com.anton.expocenterspring.services;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Hall;

import java.util.Set;

public interface ExpositionService {
    Set<Exposition> getActiveExpositionsForHall(Hall hall);

    Set<Exposition> searchExpositionsByTitle(String query);

    Exposition save(Exposition exposition);
}
