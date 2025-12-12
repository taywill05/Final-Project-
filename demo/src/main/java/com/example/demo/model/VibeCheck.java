package com.example.demo.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "vibe_checks")
public class VibeCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MANY vibe checks belong to ONE user
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "vibe_name", nullable = false)
    private String vibeName;

    @Column(name = "emoji", nullable = false)
    private String emoji;

    @Column(name = "quote", columnDefinition = "TEXT")
    private String quote;

    @Column(name = "questions", columnDefinition = "TEXT")
    private String questions; // JSON string

    @Column(name = "answers", columnDefinition = "TEXT")
    private String answers; // JSON string

    @Column(name = "date_created", nullable = false)
    private OffsetDateTime dateCreated;

    public VibeCheck() {}

    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getVibeName() { return vibeName; }
    public String getEmoji() { return emoji; }
    public String getQuote() { return quote; }
    public String getQuestions() { return questions; }
    public String getAnswers() { return answers; }
    public OffsetDateTime getDateCreated() { return dateCreated; }

    public void setUser(User user) { this.user = user; }
    public void setVibeName(String vibeName) { this.vibeName = vibeName; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setQuote(String quote) { this.quote = quote; }
    public void setQuestions(String questions) { this.questions = questions; }
    public void setAnswers(String answers) { this.answers = answers; }
    public void setDateCreated(OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }
}
