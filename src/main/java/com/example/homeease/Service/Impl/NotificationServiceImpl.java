package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Notification;
import com.example.homeease.Repo.NotificationRepository;
import com.example.homeease.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(int id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public Notification updateNotification(int id, Notification notification) {
        Notification existingNotification = notificationRepository.findById(id).orElse(null);
        if (existingNotification != null) {
            existingNotification.setMessage(notification.getMessage());
            return notificationRepository.save(existingNotification);
        }
        return null;
    }

    @Override
    public void deleteNotification(int id) {
        notificationRepository.deleteById(id);
    }
}