package com.example.homeease.Repo;

import com.example.homeease.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Custom query methods can be added here
    List<Booking> findByCustomer_UserId(int customerId); // Find bookings by customer ID
    List<Booking> findByService_ServiceId(int serviceId); // Find bookings by service ID
}