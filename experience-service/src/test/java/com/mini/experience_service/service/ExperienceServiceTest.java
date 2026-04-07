package com.mini.experience_service.service;

import com.mini.experience_service.Model.Experience;
import com.mini.experience_service.Repository.ExperienceRepository;
import com.mini.experience_service.Service.ExperienceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExperienceServiceTest {

    @Mock
    private ExperienceRepository repository;

    @InjectMocks
    private ExperienceService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllExperiences() {

        Experience exp = new Experience();
        exp.setId("1");

        when(repository.findAll()).thenReturn(List.of(exp));

        List<Experience> result = service.getAllExperiences();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnEmptyList() {

        when(repository.findAll()).thenReturn(List.of());

        assertTrue(service.getAllExperiences().isEmpty());
    }

    @Test
    void shouldSaveExperience() {

        Experience exp = new Experience();
        exp.setId("1");

        when(repository.save(any(Experience.class))).thenReturn(exp);

        Experience result = service.saveExperience(exp);

        assertEquals("1", result.getId());
    }

    @Test
    void shouldDeleteExperience() {

        doNothing().when(repository).deleteById("1");

        service.deleteExperience("1");

        verify(repository).deleteById("1");
    }
}