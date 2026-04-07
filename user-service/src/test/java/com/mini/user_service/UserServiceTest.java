package com.mini.user_service;

import com.mini.user_service.Model.User;
import com.mini.user_service.Model.UserRole;
import com.mini.user_service.Repository.UserRepository;
import com.mini.user_service.Service.UserService;
import com.mini.user_service.Dto.UserResponse;
import com.mini.user_service.Dto.RegisterRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.oauth2.jwt.Jwt;

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

    // ✅ getAllUsers
    @Test
    void shouldReturnMappedUsers() {
        User user = new User();
        user.setId("1");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john@test.com");
        user.setRole(UserRole.Senior);

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponse> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Senior", result.get(0).getRole());
    }

    @Test
    void shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());
        assertTrue(userService.getAllUsers().isEmpty());
    }

    // ✅ getUserProfile
    @Test
    void shouldReturnUserProfile() {
        User user = new User();
        user.setId("1");
        user.setEmail("test@mail.com");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        UserResponse res = userService.getUserProfile("1");

        assertEquals("test@mail.com", res.getEmail());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        when(userRepository.findById("99")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.getUserProfile("99");
        });
    }

    // ✅ register
    @Test
    void shouldRegisterUser() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("new@mail.com");
        req.setPassword("123");
        req.setFirstname("New");
        req.setLastname("User");
        req.setYear(2);

        when(userRepository.existsByEmail("new@mail.com")).thenReturn(false);

        User saved = new User();
        saved.setEmail("new@mail.com");

        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserResponse res = userService.register(req);

        assertEquals("new@mail.com", res.getEmail());
    }

    @Test
    void shouldThrowIfEmailExists() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("dup@mail.com");

        when(userRepository.existsByEmail("dup@mail.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            userService.register(req);
        });
    }

    // ✅ getOrCreateUser
    @Test
    void shouldReturnExistingUserFromJwt() {
        Jwt jwt = mock(Jwt.class);
        when(jwt.getSubject()).thenReturn("kc123");

        User user = new User();
        user.setKeyCloakId("kc123");

        when(userRepository.findByKeyCloakId("kc123"))
                .thenReturn(Optional.of(user));

        UserResponse res = userService.getOrCreateUser(jwt);

        assertEquals("kc123", res.getKeyCloakId());
    }

    @Test
    void shouldCreateNewUserFromJwt() {
        Jwt jwt = mock(Jwt.class);
        when(jwt.getSubject()).thenReturn("kc999");
        when(jwt.getClaim("email")).thenReturn("new@mail.com");
        when(jwt.getClaim("given_name")).thenReturn("John");
        when(jwt.getClaim("family_name")).thenReturn("Doe");

        when(userRepository.findByKeyCloakId("kc999"))
                .thenReturn(Optional.empty());

        User saved = new User();
        saved.setKeyCloakId("kc999");

        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserResponse res = userService.getOrCreateUser(jwt);

        assertEquals("kc999", res.getKeyCloakId());
    }
}