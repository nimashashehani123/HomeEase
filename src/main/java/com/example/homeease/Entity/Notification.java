package com.example.homeease.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private String message;
    private Boolean isRead;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = true)
    private ServiceProvider provider;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    // Getters and Setters
}

