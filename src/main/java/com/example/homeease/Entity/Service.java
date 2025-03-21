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

    @Column(nullable = false)
    private double price;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Relationship with Category

    @ManyToOne
    @JoinColumn(name = "service_provider_id", nullable = false)
    private User serviceProvider; // Relationship with User (service provider)
}