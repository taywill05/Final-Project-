package com.example.demo.controller;

import com.example.demo.dto.VibeCheckRequest;
import com.example.demo.dto.VibeCheckResponse;
import com.example.demo.service.VibeCheckService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vibe-checks")
@CrossOrigin
public class VibeCheckController {

    private final VibeCheckService vibeCheckService;

    public VibeCheckController(VibeCheckService vibeCheckService) {
        this.vibeCheckService = vibeCheckService;
    }

    @PostMapping
    public VibeCheckResponse create(@Valid @RequestBody VibeCheckRequest request) {
        return vibeCheckService.createVibeCheck(request);
    }

    @GetMapping("/user/{username}")
    public List<VibeCheckResponse> getForUser(@PathVariable String username) {
        return vibeCheckService.getVibeChecksForUser(username);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vibeCheckService.deleteVibeCheck(id);
    }
}
