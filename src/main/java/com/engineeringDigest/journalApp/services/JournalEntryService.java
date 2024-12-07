package com.engineeringDigest.journalApp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import com.engineeringDigest.journalApp.entity.JournalEntry;
import com.engineeringDigest.journalApp.entity.User;
import com.engineeringDigest.journalApp.repository.JournalEntryRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An error has occured while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntry(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
        try {
            User user = userService.findByUserName(userName);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public ResponseEntity<JournalEntry> updateEntry(ObjectId id, JournalEntry newEntry) {

        JournalEntry oldJournalEntry = getJournalEntry(id).orElse(null);
        if (oldJournalEntry != null) {
            oldJournalEntry
                    .setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
                            : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(
                    newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                            : oldJournalEntry.getContent());
            saveEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
