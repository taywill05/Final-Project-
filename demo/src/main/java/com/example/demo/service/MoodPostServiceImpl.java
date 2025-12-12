package com.example.demo.service;

import com.example.demo.dto.MoodPostRequest;
import com.example.demo.dto.MoodPostResponse;
import com.example.demo.model.MoodPost;
import com.example.demo.model.User;
import com.example.demo.repository.MoodPostRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service   
public class MoodPostServiceImpl implements MoodPostService {

    private final MoodPostRepository moodPostRepository;
    private final UserRepository userRepository;

    public MoodPostServiceImpl(MoodPostRepository moodPostRepository, UserRepository userRepository) {
        this.moodPostRepository = moodPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MoodPostResponse createMoodPost(MoodPostRequest request) {

        User user = userRepository.findById(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        MoodPost post = new MoodPost();
        post.setUser(user);
        post.setMood(request.getMood());
        post.setNote(request.getNote());
        post.setEmoji(request.getEmoji());
        post.setDateCreated(now);
        

        MoodPost saved = moodPostRepository.save(post);
        return toResponse(saved);
    }

    @Override
    public List<MoodPostResponse> getMoodPostsForUser(String username) {
        return moodPostRepository
                .findByUserUsernameOrderByDateCreatedDesc(username)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<MoodPostResponse> getAllMoodPosts() {
        return moodPostRepository
                .findAll(Sort.by(Sort.Direction.DESC, "dateCreated"))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MoodPostResponse updateMoodPost(Long id, MoodPostRequest request) {
        MoodPost post = moodPostRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mood post not found"));

        if(request.getMood() != null && !request.getMood().isBlank()) {
            post.setMood(request.getMood());
        }

        post.setNote(request.getNote());
        post.setEmoji(request.getEmoji());


        MoodPost saved = moodPostRepository.save(post);
        return toResponse(saved);


    }

    @Override 
    public void deleteMoodPost(Long id) {
        moodPostRepository.deleteById(id);
    }

    private MoodPostResponse toResponse(MoodPost post) {
        return new MoodPostResponse(
                post.getId(),
                post.getUser().getUsername(),
                post.getMood(),
                post.getNote(),
                post.getEmoji(),
                post.getDateCreated()
        );
    }

}
