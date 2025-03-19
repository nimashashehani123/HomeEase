package com.example.homeease.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_provider")
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    private String name;
    private String email;
    private String username;
    private String password;
    private String contact;
    private String availability;
    private String location;
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;

    @OneToOne
    @JoinColumn(name = "verification_id", nullable = false)
    private Verification verification;

    // Getters and Setters
}

