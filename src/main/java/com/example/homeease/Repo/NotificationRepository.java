package com.example.homeease.Repo;

import com.example.homeease.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    // Find notifications by user
    List<Notification> findByUser_UserId(int userId);
}
