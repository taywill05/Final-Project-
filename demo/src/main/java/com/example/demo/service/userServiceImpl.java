package com.example.demo.service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.users;
import com.example.demo.repository.userRepository;
//import com.example.demo.service.userService;

@Service
public class userServiceImpl implements userService {
    private final userRepository userRepo;
    
    
    public userServiceImpl(userRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<users> findById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public users createUser(users usr) {
        // place validation/business rules here (e.g., uniqueness, hashing, etc.)
        return userRepo.save(usr);
    }


    // @Autowired
    // public userServiceImpl(userRepository userRepo) {
    //     this.userRepo = userRepo;
    // }

    // @Override
    // public List<users> getAllUsers() {
    //     return userRepo.findAll();
    // }
}
