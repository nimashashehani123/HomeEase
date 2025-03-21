package com.example.homeease.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private int notificationId; // Unique identifier for the notification
    private int userId; // ID of the associated user
    private String message; // Notification message
    private LocalDateTime timestamp; // Timestamp for when the notification was created
}