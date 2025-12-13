package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    private User u1;

    @BeforeEach
    void setUp() {
        u1 = new User("user1", "mooduser135");
        u1.setUsername("user1");
    }

    @Test
    void getAllUsers_returnsAll() {
        when(userRepository.findAll()).thenReturn(List.of(u1));
        List<User> all = service.getAllUsers();
        assertThat(all).hasSize(1);
    }

    @Test
    void findById_returnsOptional() {
        when(userRepository.findById("user1")).thenReturn(Optional.of(u1));
        Optional<User> opt = service.findById("user1");
        assertThat(opt).isPresent();
        assertThat(opt.get().getUsername()).isEqualTo("user1");
    }

    @Test
    void createUser_savesUser() {
        when(userRepository.save(u1)).thenReturn(u1);
        User saved = service.createUser(u1);
        verify(userRepository).save(u1);
        assertThat(saved).isSameAs(u1);
    }

}
