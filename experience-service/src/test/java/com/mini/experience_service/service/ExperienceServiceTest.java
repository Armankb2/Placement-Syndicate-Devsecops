package com.mini.experience_service.service;

import com.mini.experience_service.Dto.RegisterRequest;
import com.mini.experience_service.Dto.UserResponse;
import com.mini.experience_service.Model.Experience;
import com.mini.experience_service.Repository.ExperienceRepository;
import com.mini.experience_service.Service.ExperienceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExperienceServiceTest {

    @Mock
    private ExperienceRepository repository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private ExperienceService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ getUserProfile
    @Test
    void shouldReturnUserProfile() {
        Experience exp = new Experience();
        exp.setId("1");
        exp.setCompanyName("Google");

        when(repository.findById("1")).thenReturn(Optional.of(exp));

        UserResponse res = service.getUserProfile("1");

        assertEquals("Google", res.getCompanyName());
    }

    @Test
    void shouldThrowIfExperienceNotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.getUserProfile("1");
        });
    }

    // ✅ register
    @Test
    void shouldRegisterExperience() {
        RegisterRequest req = new RegisterRequest();
        req.setCompanyName("Amazon");

        Experience saved = new Experience();
        saved.setId("1");
        saved.setCompanyName("Amazon");

        when(repository.save(any(Experience.class))).thenReturn(saved);

        UserResponse res = service.register(req, "user1");

        assertEquals("Amazon", res.getCompanyName());
        verify(kafkaTemplate).send(anyString(), anyString());
    }

    // ✅ getByCompanyName
    @Test
    void shouldReturnExperiencesByCompany() {
        Experience exp = new Experience();
        exp.setCompanyName("Google");

        when(repository.findByCompanyName("Google"))
                .thenReturn(List.of(exp));

        List<UserResponse> result = service.getByCompanyName("Google");

        assertEquals(1, result.size());
    }

    // ✅ delete by admin
    @Test
    void shouldDeleteByAdmin() {
        Experience exp = new Experience();
        exp.setId("1");

        when(repository.findById("1")).thenReturn(Optional.of(exp));

        service.deleteExperienceByAdmin("1");

        verify(repository).delete(exp);
    }

    // ✅ delete own (success)
    @Test
    void shouldDeleteOwnExperience() {
        Experience exp = new Experience();
        exp.setId("1");
        exp.setCreatedBy("user1");

        when(repository.findById("1")).thenReturn(Optional.of(exp));

        service.deleteOwnExperience("1", "user1");

        verify(repository).delete(exp);
    }

    // ❌ delete own (unauthorized)
    @Test
    void shouldThrowIfNotOwner() {
        Experience exp = new Experience();
        exp.setId("1");
        exp.setCreatedBy("user1");

        when(repository.findById("1")).thenReturn(Optional.of(exp));

        assertThrows(RuntimeException.class, () -> {
            service.deleteOwnExperience("1", "user2");
        });
    }

    // ✅ getExperiencesByUser
    @Test
    void shouldReturnExperiencesByUser() {
        Experience exp = new Experience();
        exp.setCreatedBy("user1");

        when(repository.findByCreatedBy("user1"))
                .thenReturn(List.of(exp));

        List<UserResponse> result = service.getExperiencesByUser("user1");

        assertEquals(1, result.size());
    }
}