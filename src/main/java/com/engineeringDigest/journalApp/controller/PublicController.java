package com.engineeringDigest.journalApp.controller;

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
import com.engineeringDigest.journalApp.services.JournalEntryService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Situation normal all Fired up!";
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createUserJournalEntry(@RequestBody JournalEntry myEntry,
            @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
