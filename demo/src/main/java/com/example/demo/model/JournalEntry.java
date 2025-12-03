package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

public class JournalEntry {
    private LocalDate date;       // Stores the date of the entry
    private List<String> emojis; // Stores a list of emojis associated with the entry

    public JournalEntry(LocalDate date, List<String> emojis) {
        this.date = date;
        this.emojis = emojis;
    }

    public LocalDate getdate() {
        return date;
    }

    public List<String> getEmojis() {
        return emojis;
    }
}
