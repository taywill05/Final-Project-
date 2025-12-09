package com.example.demo.service;

import com.example.demo.model.users;
import com.example.demo.repository.userRepository;

import java.util.List;

import java.util.Optional;

public interface userService {
    List<users> getAllUsers();
    Optional<users> findById(String id);        // search by ID
    users createUser(users usr);                 // create user
}




// public class userService {
//     private final userRepository repo;

//     public userService(userRepository repo) {
//         this.repo = repo;
//     }

//     public List<users> getAllUsers(){
//         return repo.getAllUsers()
//                     .orElseThrow(() -> new RuntimeException("No users found: " ));
//     }

//     public users getByUsername(String username){
//         return repo.findById(username)
//                     .orElseThrow(() -> new RuntimeException("User not found: " + username));
//     }

//     public users createUser(users usr){
//         return repo.save(usr);
//     }
// }

