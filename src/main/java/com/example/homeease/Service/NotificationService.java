package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Notification;

import java.util.List;

public interface NotificationService {
    Notification addNotification(Notification notification);
    List<Notification> getAllNotifications();
    Notification getNotificationById(int id) throws ResourceNotFoundException;
    Notification updateNotification(int id, Notification notification) throws ResourceNotFoundException;
    void deleteNotification(int id) throws ResourceNotFoundException;
}