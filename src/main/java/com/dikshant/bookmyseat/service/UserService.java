package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.dto.AuthResponse;
import com.dikshant.bookmyseat.dto.LoginRequest;
import com.dikshant.bookmyseat.dto.UserRequest;
import com.dikshant.bookmyseat.entity.User;
import com.dikshant.bookmyseat.repository.UserRepo;
import com.dikshant.bookmyseat.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Register User
    public User register(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException(
                    "User already exists: " + userRequest.getEmail()
            );
        }

        String encodedPassword =
                passwordEncoder.encode(userRequest.getPassword());

        User user = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(encodedPassword)
                .phone(userRequest.getPhone())
                .build();

        return userRepository.save(user);
    }

    // Login
    public AuthResponse login(LoginRequest userRequest) {

        User user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        ));

        if (!passwordEncoder.matches(
                userRequest.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                "Bearer",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + id
                        ));
    }
}