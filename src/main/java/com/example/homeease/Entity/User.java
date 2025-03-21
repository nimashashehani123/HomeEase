package com.example.homeease.Entity;

import com.example.homeease.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    @Column(nullable = false)
    private UserRole role; // Use the UserRole enum

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(name = "verification_status", nullable = false)
    private String verificationStatus = "Pending"; // Pending, Verified, Rejected

    @Column(name = "id_proof_path")
    private String idProofPath; // Path to ID proof document

    @Column(name = "address_proof_path")
    private String addressProofPath; // Path to address proof document
}