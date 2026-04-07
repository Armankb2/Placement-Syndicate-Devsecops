package com.mini.notification_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserClient userClient;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUsers() {

        User user = new User();
        user.setEmail("test@mail.com");

        when(restTemplate.getForObject(anyString(), eq(User[].class)))
                .thenReturn(new User[]{user});

        List<User> result = userClient.getAllUsers();

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyListWhenNull() {

        when(restTemplate.getForObject(anyString(), eq(User[].class)))
                .thenReturn(null);

        List<User> result = userClient.getAllUsers();

        assertTrue(result.isEmpty());
    }
}