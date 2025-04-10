package com.example.homeease.Repo;

import com.example.homeease.Entity.Service;
import com.example.homeease.Entity.User;
import com.example.homeease.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query methods can be added here
    User findByEmail(String email); // Find user by email
    boolean existsByEmail(String email);
    List<User> findByRole(UserRole role); // Find users by role (e.g., CUSTOMER, SERVICE_PROVIDER)
    List<User> findByVerificationStatus(String verificationStatus); // Find users by verification status

    User findByUserId(int userId);

    @Query(value = "SELECT email FROM user" ,nativeQuery = true)
    List<User> getUsers(User user);

    @Query("SELECT u.userId FROM User u WHERE u.role = 'SERVICE_PROVIDER'")
    List<Integer> findAllServiceProviderIds();

    @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    int findUserIdByEmailAddress(@Param("email") String email);


}