package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
    }

    @Test
    void registerUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User registeredUser = userService.registerUser(user);
        assertNotNull(registeredUser);
        assertEquals("John Doe", registeredUser.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void loginUser() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(java.util.Optional.of(user));
        User loggedInUser = userService.loginUser("john.doe@example.com", "password123");
        assertNotNull(loggedInUser);
        assertEquals("John Doe", loggedInUser.getName());
    }

    @Test
    void loginUserInvalid() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(java.util.Optional.of(user));
        User loggedInUser = userService.loginUser("john.doe@example.com", "wrongpassword");
        assertNull(loggedInUser);
    }
}