package com.example.demo.dto;

public class MoodPostRequest {
    private String username;
    private String mood;
    private String note;
    private String emoji;

    public MoodPostRequest(){}

    public MoodPostRequest(String username, String mood, String note, String emoji) {
        this.username = username;
        this.mood = mood;
        this.note = note;
        this.emoji = emoji;
    }

    
    public String getUsername() {
        return username;
    }
    
    public String getMood() {
        return mood;
    }

    public String getNote() {
        return note;
    }

    public String getEmoji() {
        return emoji;
    }



    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
    
}
