package com.example.homeease.Repo;

import com.example.homeease.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Find reviews by service
    List<Review> findByBooking_Service_ServiceId(int serviceId);

    // Find reviews by customer
    List<Review> findByBooking_Customer_UserId(int customerId);
}