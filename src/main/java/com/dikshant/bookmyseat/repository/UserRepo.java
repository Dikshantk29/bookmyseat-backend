package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// Repository interface for User entity
// Extends JpaRepository to get built-in CRUD operations
public interface UserRepo extends JpaRepository<User, Long> {
   // Custom query method to find user by email
   // Returns Optional to safely handle null values
   Optional<User> findByEmail(String email);
   Boolean existsByEmail(String email);
}
