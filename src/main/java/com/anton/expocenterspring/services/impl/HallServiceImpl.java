package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.repositories.HallRepository;
import com.anton.expocenterspring.services.HallService;

import java.util.HashSet;
import java.util.Set;

public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Set<Hall> getAllHalls() {
        Set<Hall> halls = new HashSet<>();
        hallRepository.findAll().forEach(halls::add);
        return halls;
    }
}
