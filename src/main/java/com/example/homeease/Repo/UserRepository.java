package com.example.homeease.Repo;

import com.example.homeease.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Return Optional<User> instead of User
    Optional<User> findByEmail(String email);
}