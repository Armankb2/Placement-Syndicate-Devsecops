package com.mini.experience_service.service;

import com.mini.experience_service.Repository.ExperienceRepository;
import com.mini.experience_service.Service.ExperienceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @InjectMocks
    private ExperienceService experienceService;

    @Test
    void testServiceLoad() {
        assert experienceService != null;
    }
}