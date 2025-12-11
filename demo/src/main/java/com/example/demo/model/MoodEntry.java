package com.example.demo.model;

import java.time.LocalDate;


public class MoodEntry {
    private String mood;      
    private String notes; 
    private LocalDate date; 

    public MoodEntry() {}

    public MoodEntry(String mood, String notes, LocalDate date) {
        this.mood = mood;
        this.notes = notes;
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
