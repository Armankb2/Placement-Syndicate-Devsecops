package com.mini.user_service.service;

import com.mini.user_service.Model.User;
import com.mini.user_service.Repository.UserRepository;
import com.mini.user_service.Service.UserService;

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
    void shouldSaveUser() {
        User user = new User();
        user.setName("John");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.saveUser(user);

        assertNotNull(saved);
        assertEquals("John", saved.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldHandleEmptyUserList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.getAllUsers();

        assertTrue(result.isEmpty());
    }
}