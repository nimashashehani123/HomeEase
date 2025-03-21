package com.example.homeease.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private int paymentId;
    private int bookingId; // Associated booking ID
    private double amount; // Payment amount
    private String currency; // Currency (e.g., USD, INR)
    private String paymentMethod; // Payment method (e.g., Credit Card, UPI, PayPal)
    private String transactionId; // Payment gateway transaction ID
    private String status; // Payment status (e.g., Pending, Completed, Failed, Refunded)
    private LocalDateTime paymentDate; // Date and time of payment
}