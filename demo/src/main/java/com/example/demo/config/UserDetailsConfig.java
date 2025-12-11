package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConfig implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsConfig(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
    public boolean register(String username, String rawPassword) {
      
        if (userRepository.existsById(username)) {
            return false;  
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);

       
        User user = new User(username, encodedPassword);

        userRepository.save(user);  
        return true;
    }

   
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

       
        User user = userRepository.findById(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + username)
            );

        
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())  
                .authorities("ROLE_USER")       
                .build();
    }
}
