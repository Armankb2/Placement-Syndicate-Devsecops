package com.mini.experience_service.service;

import com.mini.experience_service.Entity.Experience;
import com.mini.experience_service.Repository.ExperienceRepository;
import com.mini.experience_service.Service.ExperienceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @InjectMocks
    private ExperienceService experienceService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnExperiences() {
        List<Experience> list = Arrays.asList(new Experience(), new Experience());

        when(experienceRepository.findAll()).thenReturn(list);

        List<Experience> result = experienceService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void shouldHandleEmptyList() {
        when(experienceRepository.findAll()).thenReturn(Collections.emptyList());

        assertTrue(experienceService.getAll().isEmpty());
    }
}