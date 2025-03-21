package com.example.homeease.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private int notificationId;
    private int userId; // Associated user ID
    private String message;
    private LocalDateTime timestamp;
}