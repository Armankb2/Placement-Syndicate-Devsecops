package com.mini.user_service.controller;

import com.mini.user_service.Controller.UserController;
import com.mini.user_service.Dto.UserResponse;
import com.mini.user_service.Service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldReturnUsers() throws Exception {

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(new UserResponse()));

        mockMvc.perform(get("/api/users/all"))   // ✅ EXACT MATCH
                .andExpect(status().isOk());
    }
}