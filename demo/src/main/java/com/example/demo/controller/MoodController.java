package com.example.demo.controller;


import com.example.demo.model.CreateMood;
import com.example.demo.model.VibeCheckItem;
import com.example.demo.service.MoodService;



import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/mood")
@CrossOrigin(origins = "http://localhost:5174") 
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
    
    @GetMapping("/data-display")
    public List<CreateMood> getHistory() {
        return moodService.getAllEntries();
    }

    @PostMapping("/vibe-check")
    public String saveVibeCheck(@RequestBody List<VibeCheckItem> vibeCheckItems) {
        System.out.println("Received Vibe Check Items:");
        for (VibeCheckItem item : vibeCheckItems) {
            System.out.println("Question: " + item.getQuestion() + ", Answer: " + item.getAnswer());
        }
        return "Vibe check saved successfully!";
    }


}
