package com.example.demo.repository;

import com.example.demo.model.VibeCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VibeCheckRepository extends JpaRepository<VibeCheck, Long> {
    List<VibeCheck> findByUserUsernameOrderByDateCreatedDesc(String username);
}
