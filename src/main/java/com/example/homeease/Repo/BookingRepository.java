package com.example.homeease.Repo;
import com.example.homeease.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Find bookings by customer ID
    List<Booking> findByCustomer_UserId(int customerId);

    // Find bookings by service ID
    List<Booking> findByService_ServiceId(int serviceId);

    // Find bookings by status
    List<Booking> findByStatus(String status);

    // Find bookings for a service provider
    @Query("SELECT b FROM Booking b WHERE b.service.serviceProvider.userId = :providerId")
    List<Booking> findByServiceProviderId(@Param("providerId") int providerId);

    List<Booking> findByStatusIn(List<String> pending);


    // Get bookings for a specific service provider with optional filters
    @Query(value = """
    SELECT b FROM Booking b
    WHERE b.service.serviceProvider.userId = :userId
    AND (:status IS NULL OR b.status = :status)
    AND (:fromDate IS NULL OR b.bookingDateTime >= :fromDate)
    AND (:toDate IS NULL OR b.bookingDateTime <= :toDate)
    ORDER BY b.bookingDateTime DESC
    """)
    List<Booking> findProviderBookings(
            @Param("userId") int userId,
            @Param("status") String status,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );
    @Query(value = """
    SELECT b FROM Booking b
    JOIN User u ON b.customer.userId = u.userId
    WHERE u.userId = :customerId
    AND (:status IS NULL OR b.status = :status)
    AND (:fromDate IS NULL OR b.bookingDateTime >= :fromDate)
    AND (:toDate IS NULL OR b.bookingDateTime <= :toDate)
    ORDER BY b.bookingDateTime DESC
    """)
    List<Booking> findBookingsByCustomer(
            @Param("customerId") int customerId,
            @Param("status") String status,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );

}