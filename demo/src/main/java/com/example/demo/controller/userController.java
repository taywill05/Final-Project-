package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User findUser(@PathVariable String username) {
        return userService.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }
}
