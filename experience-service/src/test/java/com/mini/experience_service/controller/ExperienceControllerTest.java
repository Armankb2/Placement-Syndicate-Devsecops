package com.mini.experience_service.controller;

import com.mini.experience_service.Controller.ExperienceController;
import com.mini.experience_service.Service.ExperienceService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExperienceController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExperienceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExperienceService service;

    @Test
    void shouldReturnCompanies() throws Exception {

        Mockito.when(service.getAllCompanies())
                .thenReturn(List.of("Google"));

        mockMvc.perform(get("/api/experience/companies"))
                .andExpect(status().isOk());
    }
}