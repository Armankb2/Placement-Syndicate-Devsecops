package com.mini.user_service;

import com.mini.user_service.Model.User;
import com.mini.user_service.Repository.UserRepository;
import com.mini.user_service.Service.UserService;
import com.mini.user_service.Dto.UserResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnMappedUsers() {

        User user = new User();
        user.setId("1");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john@test.com");
        user.setKeyCloakId("kc123");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<UserResponse> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("john@test.com", result.get(0).getEmail());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyList() {

        when(userRepository.findAll()).thenReturn(List.of());

        List<UserResponse> result = userService.getAllUsers();

        assertTrue(result.isEmpty());
    }
}