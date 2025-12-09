package com.example.demo.model;

import java.time.LocalDate;


public class CreateMood {
    private String mood;
    private String note;
    private String date;

    public CreateMood() {}

    public CreateMood(String mood, String note, String date) {
        this.mood = mood;
        this.note = note;
        this.date = date;
    }

    public String getMood(){
        return mood;
    }

    public String getNote() {
        return note;
    }

    public String getDate(){
        return date;
    }

    //Setters
    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
