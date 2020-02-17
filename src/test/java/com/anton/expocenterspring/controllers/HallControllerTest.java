package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.enums.HallType;
import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Hall;
import com.anton.expocenterspring.services.ExpositionService;
import com.anton.expocenterspring.services.HallService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HallControllerTest {
    private final static Set<Exposition> expositions = new HashSet<>();
    @Mock
    HallService hallService;
    @Mock
    ExpositionService expositionService;
    @InjectMocks
    HallController hallController;
    MockMvc mockMvc;

    @BeforeAll
    static void before() throws Exception {
        Exposition exposition1 = new Exposition();
        Exposition exposition2 = new Exposition();

        expositions.add(exposition1);
        expositions.add(exposition2);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(hallController).build();
    }

    @Test
    void getAllHallsTest() throws Exception {
        Set<Hall> halls = new HashSet<>();
        Hall hall1 = new Hall();
        Hall hall2 = new Hall();

        halls.add(hall1);
        halls.add(hall2);

        when(hallService.getAllHalls()).thenReturn(halls);

        mockMvc.perform(get("/").sessionAttr("mailSuccess", "success"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("halls"))
                .andExpect(model().attributeExists("mailSuccess"));
    }

    @Test
    void getLargeHallTest() throws Exception {
        Hall hall = new Hall();
        hall.setType(HallType.LARGE);

        when(hallService.getHallByType(HallType.LARGE)).thenReturn(hall);
        when(expositionService.getActiveExpositionsForHall(any(Hall.class))).thenReturn(expositions);

        mockMvc.perform(get("/largeHall"))
                .andExpect(status().isOk())
                .andExpect(view().name("hall"))
                .andExpect(model().attributeExists("hall"))
                .andExpect(model().attributeExists("expositions"));
    }

    @Test
    void getMediumHallTest() throws Exception {
        Hall hall = new Hall();
        hall.setType(HallType.MEDIUM);

        when(hallService.getHallByType(HallType.MEDIUM)).thenReturn(hall);
        when(expositionService.getActiveExpositionsForHall(any(Hall.class))).thenReturn(expositions);

        mockMvc.perform(get("/mediumHall"))
                .andExpect(status().isOk())
                .andExpect(view().name("hall"))
                .andExpect(model().attributeExists("hall"))
                .andExpect(model().attributeExists("expositions"));
    }

    @Test
    void getSmallHallTest() throws Exception {
        Hall hall = new Hall();
        hall.setType(HallType.SMALL);

        when(hallService.getHallByType(HallType.SMALL)).thenReturn(hall);
        when(expositionService.getActiveExpositionsForHall(any(Hall.class))).thenReturn(expositions);

        mockMvc.perform(get("/smallHall"))
                .andExpect(status().isOk())
                .andExpect(view().name("hall"))
                .andExpect(model().attributeExists("hall"))
                .andExpect(model().attributeExists("expositions"));
    }
}