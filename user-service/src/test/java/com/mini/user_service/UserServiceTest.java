package com.mini.user_service;

import com.mini.user_service.Dto.UserResponse;
import com.mini.user_service.Service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
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

        when(userService.getAllUsers()).thenReturn(users);

        List<UserResponse> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyList() {

        when(userService.getAllUsers()).thenReturn(List.of());

        List<UserResponse> result = userService.getAllUsers();

        assertTrue(result.isEmpty());
    }
}