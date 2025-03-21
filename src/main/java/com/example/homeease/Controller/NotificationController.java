package com.example.homeease.Controller;

import com.example.homeease.Entity.Notification;
import com.example.homeease.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification addNotification(@RequestBody Notification notification) {
        return notificationService.addNotification(notification);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable int id) {
        return notificationService.getNotificationById(id);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable int id, @RequestBody Notification notification) {
        return notificationService.updateNotification(id, notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable int id) {
        notificationService.deleteNotification(id);
    }
}