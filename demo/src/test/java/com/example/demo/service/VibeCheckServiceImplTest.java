package com.example.demo.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.VibeCheckRequest;
import com.example.demo.dto.VibeCheckResponse;
import com.example.demo.model.User;
import com.example.demo.model.VibeCheck;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VibeCheckRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class VibeCheckServiceImplTest {

    @Mock
    private VibeCheckRepository vibeCheckRepository;

    @Mock
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private VibeCheckServiceImpl service;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("green", "color2");
        user.setUsername("green");
        // manually inject objectMapper since the ctor expects one
        service = new VibeCheckServiceImpl(vibeCheckRepository, userRepository, objectMapper);
    }

    @Test
    void createVibeCheck_throwsWhenQuestionsOrAnswersMissing() {
        VibeCheckRequest req = new VibeCheckRequest();
        req.setUsername("green");
        // missing both
        assertThrows(RuntimeException.class, () -> service.createVibeCheck(req));
    }

    @Test
    void createVibeCheck_throwsOnCountMismatch() {
        VibeCheckRequest req = new VibeCheckRequest();
        req.setUsername("green");
        req.setQuestions(List.of("q1", "q2"));
        req.setAnswers(List.of("a1"));

        assertThrows(RuntimeException.class, () -> service.createVibeCheck(req));
    }

    @Test
    void createVibeCheck_savesAndReturnsResponse() {
        VibeCheckRequest req = new VibeCheckRequest();
        req.setUsername("green");
        req.setQuestions(List.of("q1", "q2"));
        req.setAnswers(List.of("a1", "a2"));
        req.setVibeName("Test Vibe");
        req.setEmoji(":)");
        req.setQuote("quote");

        when(userRepository.findById("green")).thenReturn(Optional.of(user));
        when(vibeCheckRepository.save(any(VibeCheck.class))).thenAnswer(inv -> {
            VibeCheck v = inv.getArgument(0);
            v.setDateCreated(OffsetDateTime.now());
            return v;
        });

        VibeCheckResponse resp = service.createVibeCheck(req);
        assertThat(resp.getUsername()).isEqualTo("green");
        assertThat(resp.getQuestions()).containsExactly("q1", "q2");
        assertThat(resp.getAnswers()).containsExactly("a1", "a2");
    }

}
