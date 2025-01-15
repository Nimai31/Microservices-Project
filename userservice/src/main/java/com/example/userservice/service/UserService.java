package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register user (save directly to the database)
    public String registerUser(User user) {
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login user (check if username and password match)
    public String loginUser(String username, String password) {
        User user = userRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
}
