package com.dikshant.bookmyseat.service;


import com.dikshant.bookmyseat.dto.LoginRequest;
import com.dikshant.bookmyseat.dto.UserRequest;
import com.dikshant.bookmyseat.entity.User;
import com.dikshant.bookmyseat.repository.UserRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class UserService {
    private final UserRepo userRepository;


    //Register User
    public User register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User already exists" + userRequest.getEmail());
        }
        User user = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .phone(userRequest.getPhone())
                .build();
        return userRepository.save(user);
    }

    //Login
    public User login(LoginRequest userRequest){
        User user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found with email: " + userRequest.getEmail()));

        if(!user.getPassword().equals(userRequest.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return user;

    }

    //Get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //Get user by id
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));
    }


}
