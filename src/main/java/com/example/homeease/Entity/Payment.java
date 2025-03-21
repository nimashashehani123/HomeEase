package com.example.homeease.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false) // Fixed: Use column name "booking_id"
    private Booking booking; // Associated booking

    @Column(nullable = false)
    private double amount; // Payment amount

    @Column(nullable = false)
    private String currency; // Currency (e.g., USD, INR)

    @Column(nullable = false)
    private String paymentMethod; // Payment method (e.g., Credit Card, UPI, PayPal)

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId; // Payment gateway transaction ID

    @Column(nullable = false)
    private String status; // Payment status (e.g., Pending, Completed, Failed, Refunded)

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate; // Date and time of payment
}