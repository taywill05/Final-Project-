package com.example.demo.repository;

import com.example.demo.model.MoodPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoodPostRepository extends JpaRepository<MoodPost, Long> {
    List<MoodPost> findByUserUsernameOrderByDateCreatedDesc(String username);
}
