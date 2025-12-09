package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"moodPosts\"")
public class moodPosts {
    
    @Id
    @Column(name= "\"username\"", nullable=false, unique=true)
    private String username;

    @Column(name= "data_created", nullable=false)
    private String password;

    @Column(name= "\"note\"", nullable=false)
    private String firstName;
    
    @Column(name= "\"lastName\"", nullable=false)
    private String lastName;

    protected users() {}

    protected users(String password, String firstName, String lastName) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public String getUsername() {
        return username;
    }   
    public String getPassword() {
        return password;
    }       
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    //Setters
    public void setUsername(String username) {
        this.username = username;
    }           
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "users [username=" + username + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }
}