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
public class NotificationDTO {

    @NotNull
    private Long notificationId;

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Read status is required")
    private Boolean isRead;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;

    private Long providerId;
    private Long customerId;

    // Getters and Setters
}
