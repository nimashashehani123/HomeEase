package com.example.homeease.Service;
import com.example.homeease.Dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO addNotification(NotificationDTO notificationDTO);
    void deleteNotification(int notificationId);
    NotificationDTO getNotificationById(int notificationId);
    List<NotificationDTO> getNotificationsByUser(int userId);
}