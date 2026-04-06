package com.mini.user_service;

import com.mini.user_service.Repository.UserRepository;
import com.mini.user_service.Service.UserService;
import com.mini.user_service.Dto.UserResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

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
    void shouldReturnUsers() {
        List<UserResponse> users = Arrays.asList(
                new UserResponse(),
                new UserResponse()
        );

        // mock repository or service behavior depending on implementation
        // adjust method name if needed
        when(userService.getAllUsers()).thenReturn(users);

        List<UserResponse> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    void shouldHandleEmptyList() {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        List<UserResponse> result = userService.getAllUsers();

        assertTrue(result.isEmpty());
    }
}