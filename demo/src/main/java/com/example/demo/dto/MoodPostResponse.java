package com.example.demo.dto;

import java.time.OffsetDateTime;

public class MoodPostResponse {

    private Long id;
    private String username;
    private String mood;
    private String note;
    private String emoji;
    private OffsetDateTime dateCreated;

    public MoodPostResponse() {}

    public MoodPostResponse(
        Long id,
        String username,
        String mood,
        String note,
        String emoji,
        OffsetDateTime dateCreated) {
        
        this.id = id;
        this.username = username;
        this.mood = mood;
        this.note = note;
        this.emoji = emoji;
        this.dateCreated = dateCreated;
    }

    public Long getId() { return id; }
    public String getUsername(){return username;}
    public String getMood() { return mood; }
    public String getNote() { return note; }
    public String getEmoji() { return emoji; }
    public OffsetDateTime getDateCreated() { return dateCreated; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) {this.username = username;}
    public void setMood(String mood) { this.mood = mood; }
    public void setNote(String note) { this.note = note; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setDateCreated(OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }
}
