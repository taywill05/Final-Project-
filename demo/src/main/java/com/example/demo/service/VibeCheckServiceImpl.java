package com.example.demo.service;

import com.example.demo.dto.VibeCheckRequest;
import com.example.demo.dto.VibeCheckResponse;
import com.example.demo.model.User;
import com.example.demo.model.VibeCheck;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VibeCheckRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class VibeCheckServiceImpl implements VibeCheckService {

    private final VibeCheckRepository vibeCheckRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public VibeCheckServiceImpl(
            VibeCheckRepository vibeCheckRepository,
            UserRepository userRepository,
            ObjectMapper objectMapper
    ) {
        this.vibeCheckRepository = vibeCheckRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public VibeCheckResponse createVibeCheck(VibeCheckRequest request) {
        if (request.getQuestions() == null || request.getAnswers() == null) {
            throw new RuntimeException("Questions and answers are required");
        }
        if (request.getQuestions().size() != request.getAnswers().size()) {
            throw new RuntimeException("Questions and answers count must match");
        }

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUsername()));

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        try {
            String questionsJson = objectMapper.writeValueAsString(request.getQuestions());
            String answersJson = objectMapper.writeValueAsString(request.getAnswers());

            // IMPORTANT:
            // This uses the no-arg constructor + setters.
            // That means your VibeCheck() constructor must be public or package-private (not protected).
            VibeCheck vc = new VibeCheck();
            vc.setUser(user);
            vc.setVibeName(request.getVibeName());
            vc.setEmoji(request.getEmoji());
            vc.setQuote(request.getQuote());
            vc.setQuestions(questionsJson);
            vc.setAnswers(answersJson);
            vc.setDateCreated(now);

            VibeCheck saved = vibeCheckRepository.save(vc);
            return toResponse(saved);

        } catch (Exception e) {
            throw new RuntimeException("Failed to save vibe check", e);
        }
    }

    @Override
    public List<VibeCheckResponse> getVibeChecksForUser(String username) {
        return vibeCheckRepository.findByUserUsernameOrderByDateCreatedDesc(username)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deleteVibeCheck(Long id) {
        vibeCheckRepository.deleteById(id);
    }

    private VibeCheckResponse toResponse(VibeCheck vc) {
        try {
            List<String> questions = objectMapper.readValue(
                    vc.getQuestions(), new TypeReference<List<String>>() {}
            );
            List<String> answers = objectMapper.readValue(
                    vc.getAnswers(), new TypeReference<List<String>>() {}
            );

            return new VibeCheckResponse(
                    vc.getId(),
                    vc.getUser().getUsername(),
                    questions,
                    answers,
                    vc.getVibeName(),
                    vc.getEmoji(),
                    vc.getQuote(),
                    vc.getDateCreated()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse vibe check JSON", e);
        }
    }
}
