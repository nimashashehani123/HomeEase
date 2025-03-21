package com.example.homeease.Repo;

import com.example.homeease.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Custom query methods can be added here
    List<Review> findByBooking_BookingId(int bookingId); // Find reviews by booking ID
}