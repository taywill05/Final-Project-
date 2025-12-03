package com.example.demo.service;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.example.demo.model.JournalEntry;

@Service
public class JournalService {
    private final List<JournalEntry> entries = new ArrayList<>();

    public void addEntry(List<String> emojis) {
        entries.add(new JournalEntry(LocalDate.now(), emojis));
    }

    public List<JournalEntry> getAllEntries() {
        return entries;
    }
}
