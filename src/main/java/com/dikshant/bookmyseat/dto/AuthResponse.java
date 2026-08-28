package com.dikshant.bookmyseat.dto;

import com.dikshant.bookmyseat.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String type;
    private Long id;
    private String name;
    private String email;
    private Role role;
}