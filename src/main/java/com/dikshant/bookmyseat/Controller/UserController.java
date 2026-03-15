package com.dikshant.bookmyseat.Controller;

import com.dikshant.bookmyseat.dto.LoginRequest;
import com.dikshant.bookmyseat.dto.UserRequest;
import com.dikshant.bookmyseat.entity.User;
import com.dikshant.bookmyseat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //means Controller + Response Body
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.register(userRequest));

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest userRequest) {
        return ResponseEntity.ok(userService.login(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }


}
