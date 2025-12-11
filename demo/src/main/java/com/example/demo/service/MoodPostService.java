package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.MoodPostRequest;
import com.example.demo.dto.MoodPostResponse;

public interface MoodPostService {

    MoodPostResponse createMoodPost(MoodPostRequest request);

    List<MoodPostResponse> getMoodPostsForUser(String username);

    List<MoodPostResponse> getAllMoodPosts();
}
