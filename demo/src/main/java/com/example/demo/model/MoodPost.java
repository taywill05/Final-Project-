package com.example.demo.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;


@Entity
@Table(name = "moodposts")
public class MoodPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(nullable = false)
    private String mood;

    @Column(length = 1000)
    private String note;

    private String emoji;

    @Column(name = "date_created")
    private OffsetDateTime dateCreated;

    public MoodPost() {
    }

    
    public MoodPost(User user, String mood, String note, String emoji, OffsetDateTime dateCreated) {
        this.user = user;
        this.mood = mood;
        this.note = note;
        this.emoji = emoji;
        this.dateCreated = dateCreated;
    }

    
    public Long getId() { return id;}
    public User getUser(){return user;}
    public String getMood() { return mood; }
    public String getNote() { return note; }
    public String getEmoji() { return emoji; }
    public OffsetDateTime getDateCreated() { return dateCreated; }

    
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) {this.user = user;}
    public void setMood(String mood) { this.mood = mood; }
    public void setNote(String note) { this.note = note; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setDateCreated(OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }
}
