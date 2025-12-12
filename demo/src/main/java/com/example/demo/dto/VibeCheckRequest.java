package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class VibeCheckRequest {

    @NotBlank
    private String username;

    @NotEmpty
    private List<String> questions;

    @NotEmpty
    private List<String> answers;

    @NotBlank
    private String vibeName;

    @NotBlank
    private String emoji;

    @NotBlank
    private String quote;

    public VibeCheckRequest() {}

    public String getUsername() { return username; }
    public List<String> getQuestions() { return questions; }
    public List<String> getAnswers() { return answers; }
    public String getVibeName() { return vibeName; }
    public String getEmoji() { return emoji; }
    public String getQuote() { return quote; }

    public void setUsername(String username) { this.username = username; }
    public void setQuestions(List<String> questions) { this.questions = questions; }
    public void setAnswers(List<String> answers) { this.answers = answers; }
    public void setVibeName(String vibeName) { this.vibeName = vibeName; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setQuote(String quote) { this.quote = quote; }
}
