package com.example.demo.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class VibeCheckResponse {

    private Long id;
    private String username;
    private List<String> questions;
    private List<String> answers;
    private String vibeName;
    private String emoji;
    private String quote;
    private OffsetDateTime dateCreated;

    public VibeCheckResponse() {}

    public VibeCheckResponse(Long id, String username,
                             List<String> questions, List<String> answers,
                             String vibeName, String emoji, String quote,
                             OffsetDateTime dateCreated) {
        this.id = id;
        this.username = username;
        this.questions = questions;
        this.answers = answers;
        this.vibeName = vibeName;
        this.emoji = emoji;
        this.quote = quote;
        this.dateCreated = dateCreated;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public List<String> getQuestions() { return questions; }
    public List<String> getAnswers() { return answers; }
    public String getVibeName() { return vibeName; }
    public String getEmoji() { return emoji; }
    public String getQuote() { return quote; }
    public OffsetDateTime getDateCreated() { return dateCreated; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setQuestions(List<String> questions) { this.questions = questions; }
    public void setAnswers(List<String> answers) { this.answers = answers; }
    public void setVibeName(String vibeName) { this.vibeName = vibeName; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setQuote(String quote) { this.quote = quote; }
    public void setDateCreated(OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }
}
