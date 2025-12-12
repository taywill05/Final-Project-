package com.example.demo.service;

import com.example.demo.dto.VibeCheckRequest;
import com.example.demo.dto.VibeCheckResponse;

import java.util.List;

public interface VibeCheckService {
    VibeCheckResponse createVibeCheck(VibeCheckRequest request);
    List<VibeCheckResponse> getVibeChecksForUser(String username);
    void deleteVibeCheck(Long id);
}
