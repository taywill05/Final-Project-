package com.example.demo.config;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConfig implements UserDetailsService {

    private final Map<String, String> users = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    public UserDetailsConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        // create a default user for testing
        users.put("user", passwordEncoder.encode("password"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String pw = users.get(username);
        if (pw == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(username, pw, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public boolean register(String username, String rawPassword) {
        if (users.containsKey(username)) return false;
        users.put(username, passwordEncoder.encode(rawPassword));
        return true;
    }
}
