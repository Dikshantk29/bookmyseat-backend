package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.dto.LoginRequest;
import com.dikshant.bookmyseat.dto.UserRequest;
import com.dikshant.bookmyseat.entity.User;
import com.dikshant.bookmyseat.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    //Register User
    public User register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User already exists " + userRequest.getEmail());
        }

        // Hash the password before saving
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

        User user = User.builder().email(userRequest.getEmail()).name(userRequest.getName()).password(encodedPassword) // Save encoded password
                .phone(userRequest.getPhone()).build();
        return userRepository.save(user);
    }

    //Login
// Inside UserService.java
    public User login(LoginRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found with email: " + userRequest.getEmail()));

        // FIX: Use passwordEncoder.matches() instead of .equals()
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    //Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    //Get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


}
