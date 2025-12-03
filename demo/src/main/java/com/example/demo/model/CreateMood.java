package com.example.demo.model;

import java.time.LocalDate;


public class CreateMood {
    private LocalDate date;
    private MoodEntry newMood;

    public CreateMood() {}

    public CreateMood(LocalDate date, MoodEntry newMood) {
        this.date = date;
        this.newMood = newMood;
    }

    //Getters
    public LocalDate getDate() {
        return date;
    }

    public MoodEntry getNewMood() {
        return newMood;
    }

    //Setters
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setNewMood(MoodEntry newMood) {
        this.newMood = newMood;
    }
}
