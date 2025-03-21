package com.example.homeease.Controller;

import com.example.homeease.Dto.NotificationDTO;
import com.example.homeease.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add")
    public NotificationDTO addNotification(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.addNotification(notificationDTO);
    }

    @DeleteMapping("/delete/{notificationId}")
    public void deleteNotification(@PathVariable int notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    @GetMapping("/{notificationId}")
    public NotificationDTO getNotificationById(@PathVariable int notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @GetMapping("/by-user/{userId}")
    public List<NotificationDTO> getNotificationsByUser(@PathVariable int userId) {
        return notificationService.getNotificationsByUser(userId);
    }
}