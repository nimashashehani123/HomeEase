package com.example.homeease.Repo;
import com.example.homeease.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Find payments by booking
    List<Payment> findByBooking_BookingId(int bookingId);

    // Find payments by status
    List<Payment> findByStatus(String status);
}