package com.example.homeease.Repo;

import com.example.homeease.Entity.User;
import com.example.homeease.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query methods can be added here
    Optional<User> findByEmail(String email); // Find user by email
    List<User> findByRole(UserRole role); // Find users by role (e.g., CUSTOMER, SERVICE_PROVIDER)
    List<User> findByVerificationStatus(String verificationStatus); // Find users by verification status
}