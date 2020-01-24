package com.anton.expocenterspring.services;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Hall;

import java.util.Set;

public interface HallService {
    Set<Hall> getAllHalls();

    Hall getHallByType(HallType type);
}
