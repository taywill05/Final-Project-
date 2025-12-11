package com.example.demo.service;

import com.example.demo.dto.MoodPostRequest;
import com.example.demo.dto.MoodPostResponse;
import com.example.demo.model.MoodPost;
import com.example.demo.repository.MoodPostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service   
public class MoodPostServiceImpl implements MoodPostService {

    private final MoodPostRepository moodPostRepository;

    public MoodPostServiceImpl(MoodPostRepository moodPostRepository) {
        this.moodPostRepository = moodPostRepository;
    }

    @Override
    public MoodPostResponse createMoodPost(MoodPostRequest request) {

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        MoodPost entity = new MoodPost();
        entity.setUsername(request.getUsername());
        entity.setMood(request.getMood());
        entity.setNote(request.getNote());
        entity.setEmoji(request.getEmoji());
        entity.setDateCreated(now);
        

        MoodPost saved = moodPostRepository.save(entity);
        return toResponse(saved);
    }

    @Override
    public List<MoodPostResponse> getMoodPostsForUser(String username) {
        return moodPostRepository
                .findByUsernameOrderByDateCreatedDesc(username)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MoodPostResponse> getAllMoodPosts() {
        return moodPostRepository
                .findAll(Sort.by(Sort.Direction.DESC, "dateCreated"))
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MoodPostResponse toResponse(MoodPost post) {
        return new MoodPostResponse(
                post.getId(),
                post.getUsername(),
                post.getMood(),
                post.getNote(),
                post.getEmoji(),
                post.getDateCreated()
        );
    }
}
