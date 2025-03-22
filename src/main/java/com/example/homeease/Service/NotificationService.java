package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.NotificationDTO;

public interface NotificationService {
    ResponseDTO addNotification(NotificationDTO notificationDTO);
    ResponseDTO getAllNotifications();
    ResponseDTO getNotificationById(int notificationId);
    ResponseDTO updateNotification(int notificationId, NotificationDTO notificationDTO);
    ResponseDTO deleteNotification(int notificationId);
}