package com.example.demo.service;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

import com.example.demo.model.CreateMood;

@Service
public class MoodService {
    private final List<CreateMood> entries = new ArrayList<>();

    public void addEntry(CreateMood entry) {
        entries.add(entry);
    }

    public List<CreateMood> getAllEntries() {
        return entries;
    }
}

