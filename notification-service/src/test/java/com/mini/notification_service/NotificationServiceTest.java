package com.mini.notification_service;

import com.mini.notification_service.Service.NotificationService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    @Test
    void shouldCreateService() {
        NotificationService service = new NotificationService();
        assertNotNull(service);
    }

    @Test
    void shouldSendNotification() {
        NotificationService service = new NotificationService();

        String result = service.sendNotification("test");

        assertNotNull(result);
    }
}