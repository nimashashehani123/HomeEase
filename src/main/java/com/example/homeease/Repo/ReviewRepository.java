package com.example.homeease.Repo;

import com.example.homeease.Entity.Booking;
import com.example.homeease.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // In ReviewRepository.java
    Optional<Review> findByBooking(Booking booking);
    boolean existsByBooking(Booking booking);
}