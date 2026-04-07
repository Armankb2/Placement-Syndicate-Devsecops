package com.mini.notification_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;

class ExperienceConsumerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private ExperienceConsumer consumer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendEmailToAllUsersOnMessage() {

        User user1 = new User();
        user1.setEmail("user1@mail.com");

        User user2 = new User();
        user2.setEmail("user2@mail.com");

        when(userClient.getAllUsers()).thenReturn(List.of(user1, user2));

        consumer.consume("New experience added");

        verify(emailService, times(2))
                .sendEmail(anyString(), eq("New Experience Added"), anyString());
    }
}