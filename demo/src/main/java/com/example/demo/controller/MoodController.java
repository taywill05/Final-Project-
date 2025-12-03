package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.CreateMood;
import com.example.demo.service.MoodService;


import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/mood")
public class MoodController {
    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Mood Journal!";
    }

    @PostMapping("/add")
    public String addEntry(@RequestBody CreateMood entry ) {
        moodService.addEntry(entry);
        return "Entry added successfully!";
    }
    
    @GetMapping("/history")
    public List<CreateMood> getHistory() {
        return moodService.getAllEntries();
    }
}
