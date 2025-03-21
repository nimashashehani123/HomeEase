package com.example.homeease.Repo;

import com.example.homeease.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Find bookings by customer
    List<Booking> findByCustomer_UserId(int customerId);

    // Find bookings by service provider
    List<Booking> findByService_ServiceProvider_UserId(int serviceProviderId);
}