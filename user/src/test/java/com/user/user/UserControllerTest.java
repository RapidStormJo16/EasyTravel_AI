package com.user.user;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.user.controller.UserController;
import com.user.user.entity.User;
import com.user.user.service.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser_Success() {
        // Given
        User user = new User(1L, "john_doe", "john@example.com", "password");

        // Mock service behavior
        when(userService.createUser(any(User.class))).thenReturn(user);

        // When
        ResponseEntity<String> response = userController.createUser(user);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created!", response.getBody());
    }

    @Test
    public void testCreateUser_NullUser() {
        // When
        ResponseEntity<String> response = userController.createUser(null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User is null", response.getBody());
    }

    @Test
    public void testLoginUser_Success() {
        // Given
        String email = "john@example.com";
        String password = "password";
        User user = new User(1L, "john_doe", email, password);

        // Mock service behavior
        when(userService.loginUser(email, password)).thenReturn(user);

        // When
        ResponseEntity<?> response = userController.loginUser(email, password);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        // Given
        String email = "john@example.com";
        String password = "wrong_password";

        // Mock service behavior
        when(userService.loginUser(email, password)).thenThrow(new RuntimeException("Invalid credentials"));

        // When
        ResponseEntity<?> response = userController.loginUser(email, password);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: Invalid credentials", response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "user1", "user1@example.com", "password1"));
        userList.add(new User(2L, "user2", "user2@example.com", "password2"));

        // Mock service behavior
        when(userService.findAllUsers()).thenReturn(userList);

        // When
        List<User> result = userController.allData();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteUser_Success() {
        // Given
        long userId = 1L;
        String message = "User with id: 1 deleted successfully";

        // Mock service behavior
        when(userService.deleteUser(userId)).thenReturn(message);

        // When
        ResponseEntity<String> response = userController.deleteUser(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testDeleteUser_NotFound() {
        // Given
        long userId = 2L;

        // Mock service behavior
        when(userService.deleteUser(userId)).thenThrow(new IllegalArgumentException("User not found"));

        // When
        ResponseEntity<String> response = userController.deleteUser(userId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}
