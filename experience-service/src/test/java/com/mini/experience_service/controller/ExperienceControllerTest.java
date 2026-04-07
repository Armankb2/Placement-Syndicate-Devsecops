package com.mini.experience_service.controller;

import com.mini.experience_service.Controller.ExperienceController;
import com.mini.experience_service.Model.Experience;
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
    void shouldReturnExperiences() throws Exception {

        Mockito.when(service.getAllExperiences())
                .thenReturn(List.of(new Experience()));

        mockMvc.perform(get("/api/experience/all"))
                .andExpect(status().isOk());
    }
}