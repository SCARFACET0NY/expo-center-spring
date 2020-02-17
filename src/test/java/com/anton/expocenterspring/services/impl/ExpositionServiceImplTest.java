package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.repositories.ExpositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpositionServiceImplTest {
    private static final long ID1 = 1L;
    private static final long ID2 = 2L;
    private static final String TITLE = "exposition 1";
    private static final Set<Exposition> expositions = new HashSet<>();
    @Mock
    ExpositionRepository expositionRepository;
    @InjectMocks
    ExpositionServiceImpl expositionService;

    @BeforeEach
    void setUp() {
        Exposition exposition1 = new Exposition();
        Exposition exposition2 = new Exposition();
        exposition1.setId(ID1);
        exposition2.setId(ID2);

        expositions.add(exposition1);
        expositions.add(exposition2);
    }

    @Test
    void getAllActiveExpositionsTest() throws Exception {
        when(expositionRepository.findExpositionsByEndDateGreaterThanEqual(any(LocalDate.class)))
                .thenReturn(expositions);

        Set<Exposition> returnedExposition = expositionService.getAllActiveExpositions();


        assertNotNull(returnedExposition);
        assertEquals(expositions, returnedExposition);

        verify(expositionRepository).findExpositionsByEndDateGreaterThanEqual(any(LocalDate.class));
    }

    @Test
    void getActiveExpositionsForHallTest() throws Exception {
        Hall hall = new Hall();
        hall.setId(ID1);

        when(expositionRepository.findExpositionsByHall_IdAndEndDateGreaterThanEqual(anyLong(), any(LocalDate.class)))
                .thenReturn(expositions);

        Set<Exposition> returnedExposition = expositionService.getActiveExpositionsForHall(hall);

        assertNotNull(returnedExposition);
        assertEquals(expositions, returnedExposition);

        verify(expositionRepository).findExpositionsByHall_IdAndEndDateGreaterThanEqual(anyLong(), any(LocalDate.class));
    }

    @Test
    void getExpositionByIdTest() throws Exception {
        Exposition exposition = new Exposition();
        exposition.setId(ID1);

        when(expositionRepository.findById(anyLong())).thenReturn(Optional.of(exposition));

        Exposition returnedExposition = expositionService.getExpositionById(ID1);

        assertNotNull(returnedExposition);
        assertEquals(exposition, returnedExposition);
        assertEquals(exposition.getId(), returnedExposition.getId());

        verify(expositionRepository).findById(anyLong());
    }

    @Test
    void searchExpositionsByTitleTest() throws Exception {
        when(expositionRepository.searchByTitle(anyString())).thenReturn(expositions);

        assertEquals(expositions, expositionService.searchExpositionsByTitle(TITLE));

        verify(expositionRepository).searchByTitle(anyString());
    }

    @Test
    void saveTest() throws Exception {
        Exposition exposition = new Exposition();
        exposition.setId(ID1);

        when(expositionRepository.save(any(Exposition.class))).thenReturn(exposition);

        Exposition returnedExposition = expositionService.save(exposition);

        assertNotNull(returnedExposition);
        assertEquals(exposition.getId(), returnedExposition.getId());

        verify(expositionRepository).save(any(Exposition.class));
    }
}