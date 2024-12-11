package com.engineeringDigest.journalApp.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.engineeringDigest.journalApp.entity.User;
import com.engineeringDigest.journalApp.repository.JournalEntryRepository;
import com.engineeringDigest.journalApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    public void saveAdmin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
