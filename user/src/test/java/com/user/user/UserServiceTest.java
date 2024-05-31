package com.user.user;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.user.user.entity.User;
import com.user.user.repository.UserRepository;
import com.user.user.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser_Success() {
        // Given
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("password");

        // Mock repository behavior
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User createdUser = userService.createUser(user);

        // Then
        assertNotNull(createdUser);
        assertEquals("john_doe", createdUser.getUsername());
        assertEquals("john@example.com", createdUser.getEmail());
        assertEquals("password", createdUser.getPassword());
    }

    @Test
    public void testCreateUser_InvalidUsername() {
        // Given
        User user = new User(); // username is null

        // When-Then
        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    public void testLoginUser_Success() {
        // Given
        String email = "john@example.com";
        String password = "password";
        User user = new User(1L, "john_doe", email, password);

        // Mock repository behavior
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(user);

        // When
        User loggedInUser = userService.loginUser(email, password);

        // Then
        assertNotNull(loggedInUser);
        assertEquals("john_doe", loggedInUser.getUsername());
        assertEquals(email, loggedInUser.getEmail());
        assertEquals(password, loggedInUser.getPassword());
    }

    @Test
    public void testFindById_UserExists() {
        // Given
        Long userId = 1L;
        User user = new User(userId, "jane_doe", "jane@example.com", "password");

        // Mock repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        User foundUser = userService.findById(userId);

        // Then
        assertNotNull(foundUser);
        assertEquals("jane_doe", foundUser.getUsername());
        assertEquals("jane@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    public void testFindById_UserNotExists() {
        // Given
        Long userId = 2L;

        // Mock repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When-Then
        assertNull(userService.findById(userId));
    }

    @Test
    public void testFindAllUsers_Success() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "user1", "user1@example.com", "password1"));
        userList.add(new User(2L, "user2", "user2@example.com", "password2"));

        // Mock repository behavior
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> foundUsers = userService.findAllUsers();

        // Then
        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
    }

    @Test
    public void testDeleteUser_Success() {
        // Given
        Long userId = 1L;

        // Mock repository behavior
        when(userRepository.existsById(userId)).thenReturn(true);

        // When
        String result = userService.deleteUser(userId);

        // Then
        assertEquals("Your account with id:1 is deleted successfully", result);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        // Given
        Long userId = 2L;

        // Mock repository behavior
        when(userRepository.existsById(userId)).thenReturn(false);

        // When-Then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(userId);
        });
    }
}
