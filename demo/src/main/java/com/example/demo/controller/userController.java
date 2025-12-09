package com.example.demo.controller;

import com.example.demo.model.users;
import com.example.demo.service.userService;
import com.example.demo.repository.userRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users") // base path
public class userController {

    private userService userServ;

    public userController(userService userServ) {
        this.userServ = userServ;
    }

    // GET /user/getUsers
    @GetMapping("/getUsers")
    public List<users> getAllUsers() {
        return userServ.getAllUsers(); // returns JSON
    }


    @GetMapping("/{username}")
    public users findById(@PathVariable String username) {
        return userServ.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
    
    
    @PostMapping
    public users create(@RequestBody users usr) {
        return userServ.createUser(usr);
    }

}

// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.user;
// import com.example.demo.service.userService;

// @RestController
// @RequestMapping("/user")
// public class userController {
//     userService userServ;

//     @Autowired
//     public userController(userService userServ) {
//         this.userServ = userServ;
//     }

//     @GetMapping("/getUsers")
//     public ResponseEntity<?> getUsers() {
    
//     return new ResponseEntity<>(this.userServ.getAllUsers(), HttpStatus.OK);
//     }
//}
