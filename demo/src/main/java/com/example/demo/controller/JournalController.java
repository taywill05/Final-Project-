package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.JournalService;
import com.example.demo.model.JournalEntry;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/journal")
public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("path")
    public String addEntry(@RequestBody List<String> emojis) {
        if (emojis.size() != 3) {
            return "Please provide exactly 3 emojis.";
        }
        journalService.addEntry(emojis);
        return "Entry added successfully!";
    }
    
    @GetMapping("/history")
    public List<JournalEntry> getHistory() {
        return journalService.getAllEntries();
    }
}
