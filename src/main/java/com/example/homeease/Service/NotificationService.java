package com.example.homeease.Service;

import com.example.homeease.Entity.Notification;
import java.util.List;

public interface NotificationService {
    Notification addNotification(Notification notification);
    List<Notification> getAllNotifications();
    Notification getNotificationById(int id);
    Notification updateNotification(int id, Notification notification);
    void deleteNotification(int id);
}