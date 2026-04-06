package com.mini.experience_service.service;

import com.mini.experience_service.Service.ExperienceService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ExperienceServiceTest {

    @Test
    void shouldCreateService() {
        ExperienceService service = Mockito.mock(ExperienceService.class);
        assertNotNull(service);
    }
}