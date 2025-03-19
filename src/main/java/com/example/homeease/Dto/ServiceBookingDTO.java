package com.example.homeease.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceBookingDTO {

    @NotNull
    private Long bookingId;

    @NotNull(message = "Booking date is required")
    private LocalDateTime bookingDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Service start time is required")
    private LocalDateTime serviceStartTime;

    @NotNull(message = "Service end time is required")
    private LocalDateTime serviceEndTime;

    @NotNull
    private Long customerId;

    @NotNull
    private Long serviceId;

    // Getters and Setters
}
