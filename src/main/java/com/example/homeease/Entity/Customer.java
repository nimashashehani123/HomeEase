package com.example.homeease.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;
    private String username;
    private String email;
    private String password;
    private String contact;
    private String location;
    private Double discount;

    // Getters and Setters
}

