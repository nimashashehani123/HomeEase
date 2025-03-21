package com.example.homeease.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private int bookingId;
    private int customerId; // Associated customer ID
    private int serviceId; // Associated service ID
    private LocalDateTime bookingDate;
    private String status; // Pending, Accepted, Completed, Cancelled
}