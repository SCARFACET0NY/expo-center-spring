package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.repositories.HallRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HallServiceImplTest {
    @Mock
    HallRepository hallRepository;
    @InjectMocks
    HallServiceImpl hallService;

    @Test
    void getAllHalls() {
        Set<Hall> halls = new HashSet<>();
        Hall hall1 = new Hall();
        Hall hall2 = new Hall();

        halls.add(hall1);
        halls.add(hall2);

        when(hallRepository.findAll()).thenReturn(halls);

        Set<Hall> returnedHalls = hallService.getAllHalls();

        assertNotNull(returnedHalls);
        assertEquals(halls, returnedHalls);

        verify(hallRepository).findAll();
    }

    @Test
    void getHallByType() {
        Hall largeHall = new Hall();
        Hall mediumHall = new Hall();
        Hall smallHall = new Hall();

        largeHall.setType(HallType.LARGE);
        mediumHall.setType(HallType.MEDIUM);
        smallHall.setType(HallType.SMALL);

        when(hallRepository.findHallByType(any(HallType.class)))
                .thenReturn(largeHall).thenReturn(mediumHall).thenReturn(smallHall);

        assertEquals(largeHall, hallService.getHallByType(HallType.LARGE));
        assertEquals(mediumHall, hallService.getHallByType(HallType.MEDIUM));
        assertEquals(smallHall, hallService.getHallByType(HallType.SMALL));

        verify(hallRepository, times(3)).findHallByType(any(HallType.class));
    }
}