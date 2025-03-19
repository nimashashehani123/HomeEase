package com.example.homeease.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentType;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = true)
    private ServiceBooking booking;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = true)
    private EmergencyRequest request;

    // Getters and Setters
}
