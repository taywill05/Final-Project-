// package com.example.demo.controller;

// import com.example.demo.model.VibeCheckItem;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;

// import java.util.List;

// @RestController
// @RequestMapping("/mood")
// @CrossOrigin(origins = "http://localhost:5174")
// public class MoodController {

//     // other endpoints here (home, add, data-display, etc.)

//     @PostMapping("/vibe-check")
//     public String saveVibeCheck(@RequestBody List<VibeCheckItem> vibeCheckItems) {
//         System.out.println("Received Vibe Check Items:");
//         for (VibeCheckItem item : vibeCheckItems) {
//             System.out.println("Question: " + item.getQuestion()
//                     + ", Answer: " + item.getAnswer());
//         }
//         return "Vibe check received!";
//     }
// }
