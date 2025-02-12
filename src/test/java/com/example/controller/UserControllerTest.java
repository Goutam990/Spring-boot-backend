package com.example.controller;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password123");
    }

    @Test
    void registerUser_ShouldReturnCreated() {
        when(userService.registerUser(any(UserDto.class))).thenReturn(new User());

        ResponseEntity<User> response = userController.registerUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).registerUser(any(UserDto.class));
    }

    @Test
    void loginUser_ShouldReturnOk() {
        when(userService.loginUser(any(String.class), any(String.class))).thenReturn(new User());

        ResponseEntity<User> response = userController.loginUser(userDto.getEmail(), userDto.getPassword());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).loginUser(any(String.class), any(String.class));
    }
}