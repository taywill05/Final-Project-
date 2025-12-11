package com.example.demo.controller;

import com.example.demo.dto.MoodPostRequest;
import com.example.demo.dto.MoodPostResponse;
import com.example.demo.service.MoodPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood-posts")
@CrossOrigin
public class MoodPostController {

    private final MoodPostService moodPostService;

    public MoodPostController(MoodPostService moodPostService) {
        this.moodPostService = moodPostService;
    }

    @PostMapping
    public MoodPostResponse createMoodPost(@RequestBody MoodPostRequest request) {
        return moodPostService.createMoodPost(request);
    }

    @GetMapping
    public List<MoodPostResponse> getAllMoodPosts() {
        return moodPostService.getAllMoodPosts();
    }

    @GetMapping("/user/{username}")
    public List<MoodPostResponse> getMoodPostsForUser(@PathVariable String username) {
        return moodPostService.getMoodPostsForUser(username);
    }

    // @PostMapping
    // public ResponseEntity<MoodPostResponse> createMoodPost(
    //         @RequestBody MoodPostRequest request
    // ) {
    //     MoodPostResponse created = moodPostService.createMoodPost(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(created);
    // }

    // @GetMapping("/user/{username}")
    // public ResponseEntity<List<MoodPostResponse>> getPostsForUser(
    //         @PathVariable String username
    // ) {
    //     List<MoodPostResponse> posts = moodPostService.getMoodPostsForUser(username);
    //     return ResponseEntity.ok(posts);
    // }

    // @GetMapping
    // public ResponseEntity<List<MoodPostResponse>> getAllPosts() {
    //     List<MoodPostResponse> posts = moodPostService.getAllMoodPosts();
    //     return ResponseEntity.ok(posts);
    // }
}
