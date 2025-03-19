package com.example.homeease.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_category")
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String title;
    private String description;
    private String image;

    // Getters and Setters
}

