package com.mini.user_service.Controller;

import com.mini.user_service.Dto.UserResponse;
import com.mini.user_service.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Disable security for unit testing controller logic
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUserProfile() throws Exception {
        UserResponse response = new UserResponse();
        response.setId("test-user");
        response.setEmail("test@example.com");

        Mockito.when(userService.getUserProfile("test-user")).thenReturn(response);

        mockMvc.perform(get("/api/users/test-user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test-user"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}
