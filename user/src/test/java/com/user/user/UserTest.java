package com.user.user;

import org.junit.jupiter.api.Test;

import com.user.user.entity.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        // Given
        long id = 1000L;
        String username = "john";
        String email = "john@example.com";
        String password = "password";

        // When
        User user = new User(id, username, email, password);

        // Then
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testUserGettersAndSetters() {
        // Given
        User user = new User();
        long id = 1001L;
        String username = "jane_smith";
        String email = "jane@example.com";
        String password = "password123";

        // When
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        // Then
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testUserToString() {
        // Given
        long id = 1002L;
        String username = "sam_jones";
        String email = "sam@example.com";
        String password = "securepassword";
        User user = new User(id, username, email, password);

        // When
        String toStringResult = user.toString();

        // Then
        assertTrue(toStringResult.contains("id=" + id));
        assertTrue(toStringResult.contains("username=" + username));
        assertTrue(toStringResult.contains("email=" + email));
        assertTrue(toStringResult.contains("password=" + password));
    }

    @Test
    public void testUserEqualsAndHashCode() {
        // Given
        long id = 1003L;
        String username = "test_user";
        String email = "test@example.com";
        String password = "testpassword";

        User user1 = new User(id, username, email, password);
        User user2 = new User(id, username, email, password);

        // Then
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
