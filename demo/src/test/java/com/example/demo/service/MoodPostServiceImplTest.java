package com.example.demo.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.MoodPostRequest;
import com.example.demo.dto.MoodPostResponse;
import com.example.demo.model.MoodPost;
import com.example.demo.model.User;
import com.example.demo.repository.MoodPostRepository;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class MoodPostServiceImplTest {

    @Mock
    private MoodPostRepository moodPostRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MoodPostServiceImpl service;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("blue", "color1");
        user.setUsername("blue");
    }

    @Test
    void createMoodPost_savesAndReturnsResponse() {
        MoodPostRequest req = new MoodPostRequest("blue", "happy", "note", "emoji");
        when(userRepository.findById("blue")).thenReturn(Optional.of(user));

        ArgumentCaptor<MoodPost> captor = ArgumentCaptor.forClass(MoodPost.class);
        when(moodPostRepository.save(any(MoodPost.class))).thenAnswer(inv -> {
            MoodPost p = inv.getArgument(0);
            p.setId(123L);
            return p;
        });

        MoodPostResponse resp = service.createMoodPost(req);

        verify(moodPostRepository).save(captor.capture());
        MoodPost saved = captor.getValue();
        assertThat(saved.getUser().getUsername()).isEqualTo("blue");
        assertThat(saved.getMood()).isEqualTo("happy");
        assertThat(resp.getId()).isEqualTo(123L);
        assertThat(resp.getUsername()).isEqualTo("blue");
        assertThat(resp.getMood()).isEqualTo("happy");
    }

    @Test
    void getMoodPostsForUser_returnsMappedResponses() {
        MoodPost p = new MoodPost(user, "sad", "n", "e", OffsetDateTime.now());
        p.setId(1L);
        when(moodPostRepository.findByUserUsernameOrderByDateCreatedDesc("blue")).thenReturn(List.of(p));

        List<MoodPostResponse> list = service.getMoodPostsForUser("blue");

        assertThat(list).hasSize(1);
        assertThat(list.get(0).getId()).isEqualTo(1L);
        assertThat(list.get(0).getMood()).isEqualTo("sad");
    }

    @Test
    void updateMoodPost_updatesFields() {
        MoodPost p = new MoodPost(user, "happy", "old note", "old", OffsetDateTime.now());
        p.setId(5L);
        when(moodPostRepository.findById(5L)).thenReturn(Optional.of(p));
        when(moodPostRepository.save(any(MoodPost.class))).thenAnswer(inv -> inv.getArgument(0));

        MoodPostRequest req = new MoodPostRequest();
        req.setMood("angry");
        req.setNote("new note");
        req.setEmoji(":)");

        MoodPostResponse resp = service.updateMoodPost(5L, req);

        assertThat(resp.getMood()).isEqualTo("angry");
        assertThat(resp.getNote()).isEqualTo("new note");
    }

    @Test
    void createMoodPost_throwsWhenUserMissing() {
        MoodPostRequest req = new MoodPostRequest("nope", "m", "n", "e");
        when(userRepository.findById("nope")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.createMoodPost(req));
    }

}
