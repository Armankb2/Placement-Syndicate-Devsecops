package com.mini.user_service.controller;

import com.mini.user_service.Controller.UserController;
import com.mini.user_service.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testControllerLoad() {
        assert userController != null;
    }
}