package com.personalbudgettracker.budgettracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalbudgettracker.budgettracker.BudgettrackerApplication;
import com.personalbudgettracker.budgettracker.model.AuthRequest;
import com.personalbudgettracker.budgettracker.model.AuthResponse;
import com.personalbudgettracker.budgettracker.model.User;
import com.personalbudgettracker.budgettracker.repository.UserRepo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = BudgettrackerApplication.class)
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(4)
    void testDeleteUser() {
        String email = "testuser@mail.com";

        String result = authService.deleteUser(email);

        assertEquals("User deleted successfully!", result);
    }

    @Test
    @Order(2)
    void testLogin() {

        AuthRequest request = new AuthRequest();
        request.setEmail("testuser@mail.com");
        request.setPassword("testuser@123");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertNotNull(response.getToken());
    }

    @Test
    @Order(1)
    void testRegisterUser() {

        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@mail.com");
        user.setPassword("testuser@123");
        user.setCreatedAt(LocalDateTime.of(2001, 1, 1, 0, 0, 0));

        String response = authService.registerUser(user);

        assertEquals("User registered successfully!", response);
    }

    @Test
    @Order(3)
    void testUpdateUser() {

        User updatUser = new User();

        updatUser.setPassword("new@123");
        updatUser.setEmail("testuser@mail.com");
        updatUser.setUsername("testUser");

        String response = authService.updateUser("testuser@mail.com",updatUser);

        assertEquals("User updated successfully!", response);
    }
}
