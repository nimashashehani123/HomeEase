package com.example.homeease.Service.Impl;
import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.NotificationDTO;
import com.example.homeease.Entity.Notification;
import com.example.homeease.Repo.NotificationRepository;
import com.example.homeease.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationDTO addNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        // Set user, message, and timestamp
        Notification savedNotification = notificationRepository.save(notification);
        return convertToNotificationDTO(savedNotification);
    }

    @Override
    public void deleteNotification(int notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public NotificationDTO getNotificationById(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        return convertToNotificationDTO(notification);
    }

    @Override
    public List<NotificationDTO> getNotificationsByUser(int userId) {
        return notificationRepository.findByUser_UserId(userId).stream()
                .map(this::convertToNotificationDTO)
                .collect(Collectors.toList());
    }

    private NotificationDTO convertToNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setUserId(notification.getUser().getUserId());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setTimestamp(notification.getTimestamp());
        return notificationDTO;
    }
}