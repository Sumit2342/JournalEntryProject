package com.engineeringDigest.journalApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.engineeringDigest.journalApp.entity.User;
import com.engineeringDigest.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewEntry(user));
    }

    public void testFindByUserName() {
        User user = userRepository.findByUserName("amit");
        assertNotNull(user);
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
