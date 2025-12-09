package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.users;


@Repository
public interface userRepository extends JpaRepository<users, String> {
 
    @Query(value ="select username,password, firstName, lastName from \"user\"", nativeQuery = true)
    public Optional<List<users>> getAllUsers();
    public Optional<users> findById(String username);

}
