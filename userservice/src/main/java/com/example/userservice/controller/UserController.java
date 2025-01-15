package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Register a new user (no security, just simple user creation)
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Save the user to the repository
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login user (no actual login logic, just checking username and password)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);  // Assuming you have a method like this
    }
}

