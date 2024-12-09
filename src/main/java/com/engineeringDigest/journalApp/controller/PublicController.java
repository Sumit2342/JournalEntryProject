package com.engineeringDigest.journalApp.controller;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engineeringDigest.journalApp.entity.JournalEntry;
import com.engineeringDigest.journalApp.entity.User;
import com.engineeringDigest.journalApp.services.JournalEntryService;
import com.engineeringDigest.journalApp.services.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Situation normal all Fired up!";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        try {
            userService.saveNewEntry(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
