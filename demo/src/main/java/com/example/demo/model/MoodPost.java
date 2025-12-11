package com.example.demo.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;


@Entity
@Table(name = "moodposts")
public class MoodPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String mood;

    @Column(length = 1000)
    private String note;

    private String emoji;

    @Column(name = "date_created")
    private OffsetDateTime dateCreated;

    public MoodPost() {
    }

    // used when creating a new mood from the service
    public MoodPost(String username, String mood, String note, String emoji, OffsetDateTime dateCreated) {
        this.username = username;
        this.mood = mood;
        this.note = note;
        this.emoji = emoji;
        this.dateCreated = dateCreated;
    }

    // getters
    public Long getId() { return id;}
    public String getUsername(){return username;}
    public String getMood() { return mood; }
    public String getNote() { return note; }
    public String getEmoji() { return emoji; }
    public OffsetDateTime getDateCreated() { return dateCreated; }

    // setters (id is useful if you later add a DB)
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) {this.username = username;}
    public void setMood(String mood) { this.mood = mood; }
    public void setNote(String note) { this.note = note; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setDateCreated(OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }
}
