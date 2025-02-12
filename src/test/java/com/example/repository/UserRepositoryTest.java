package com.example.repository;

import com.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
    }

    @Test
    public void testSaveUser() {
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo(user.getName());
    }

    @Test
    public void testEmailUniqueConstraint() {
        userRepository.save(user);
        User anotherUser = new User();
        anotherUser.setName("Jane Doe");
        anotherUser.setEmail("john.doe@example.com");
        anotherUser.setPassword("password456");

        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(anotherUser);
        });
    }
}