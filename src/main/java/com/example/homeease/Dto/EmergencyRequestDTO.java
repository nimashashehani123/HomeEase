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
public class EmergencyRequestDTO {

    @NotNull
    private Long requestId;

    @NotBlank(message = "Request type is required")
    private String type;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Requested date is required")
    private LocalDateTime requestedDate;

    @NotNull
    private Long customerId;

    @NotNull
    private Long serviceId;

    // Getters and Setters
}

