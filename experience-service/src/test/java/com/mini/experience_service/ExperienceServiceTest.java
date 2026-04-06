package com.mini.experience_service.service;

import com.mini.experience_service.Repository.ExperienceRepository;
import com.mini.experience_service.Service.ExperienceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @InjectMocks
    private ExperienceService experienceService;

    public ExperienceServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testServiceCreation() {
        assert experienceService != null;
    }
}