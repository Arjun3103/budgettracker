package com.personalbudgettracker.budgettracker.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.personalbudgettracker.budgettracker.BudgettrackerApplication;
import com.personalbudgettracker.budgettracker.model.User;
import com.personalbudgettracker.budgettracker.repository.UserRepo;

import jakarta.transaction.Transactional;

@SpringBootTest(classes = BudgettrackerApplication.class)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @Rollback
    @Test
    void testFindByEmail() {
        User user = new User();

        user.setEmail("user@tracker.com");
        user.setPassword("user@123");
        user.setUsername("user");
        user.setCreatedAt(LocalDateTime.of(2001, 1, 1, 0, 0));
        userRepo.save(user);

        Optional<User> users = userRepo.findByEmail("user@tracker.com");

        assertEquals("user",users.get().getUsername());
    }
}
