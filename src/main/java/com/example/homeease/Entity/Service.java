package com.example.homeease.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private String description;

    @Column(name = "fixed_price")
    private double fixedPrice; // Fixed price for the service

    @Column(name = "hourly_rate")
    private double hourlyRate; // Hourly rate for time-based pricing

    @Column
    private String image; // Optional field for service image

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Many-to-one relationship with Category

    @ManyToOne
    @JoinColumn(name = "service_provider_id", nullable = false)
    private User serviceProvider; // Many-to-one relationship with User (service provider)
}