package com.example.homeease.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer; // Associated customer

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service; // Associated service

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Column(nullable = false)
    private String status; // Pending, Accepted, Completed, Cancelled

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL) // Fixed: Use field name "booking"
    private Payment payment; // Associated payment
}