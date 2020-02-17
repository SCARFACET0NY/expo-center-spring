package com.anton.expocenterspring.controllers;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.services.ExpositionService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {
    @Mock
    ExpositionService expositionService;
    @InjectMocks
    SearchController searchController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    void searchTest() throws Exception {
        Set<Exposition> expositions = new HashSet<>();
        Set<Exposition> emptySet = new HashSet<>();
        Exposition exposition1 = new Exposition();
        Exposition exposition2 = new Exposition();

        expositions.add(exposition1);
        expositions.add(exposition2);

        when(expositionService.searchExpositionsByTitle(anyString())).thenReturn(expositions).thenReturn(emptySet);

        mockMvc.perform(get("/search/").param("query", "expo"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("expositions"));

        mockMvc.perform(get("/search/").param("query", "expo25"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("emptySearch"))
                .andExpect(model().attributeDoesNotExist("expositions"));

        verify(expositionService, times(2)).searchExpositionsByTitle(anyString());
    }
}