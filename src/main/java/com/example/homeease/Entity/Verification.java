package com.example.homeease.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "verification")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationId;

    private String document;
    private String identityCardNumber;
    private Boolean isVerified;

    // Getters and Setters
}

