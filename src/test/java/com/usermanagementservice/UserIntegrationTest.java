package com.usermanagementservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagementservice.dtos.requests.UserDto;
import com.usermanagementservice.entities.User;
import com.usermanagementservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserRepository userRepository;

    private UUID userId;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user = userRepository.save(user);
        userId = user.getId();
    }

    @Test
    void createUserShouldReturnCreatedUser() throws Exception {
        UserDto request = new UserDto();
        request.setUsername("newuser");
        request.setEmail("newuser@example.com");
        request.setPassword("newpassword");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("newuser"))
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getUserByIdShouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateUserShouldReturnUpdatedUser() throws Exception {
        UserDto request = new UserDto();
        request.setUsername("updateduser");
        request.setEmail("updateduser@example.com");
        request.setPassword("updatedpassword");

        mockMvc.perform(put("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updateduser"))
                .andExpect(jsonPath("$.email").value("updateduser@example.com"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteUserShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", userId))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserWithoutAuthShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isUnauthorized());
    }
}
