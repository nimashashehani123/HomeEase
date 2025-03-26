package com.example.homeease.Service;

import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Dto.ResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    ResponseDTO createBooking(BookingDTO bookingDTO);
    ResponseDTO getBookingById(int bookingId);
    ResponseDTO getBookingsByCustomer(int customerId);
    ResponseDTO getBookingsByService(int serviceId);
    ResponseDTO getBookingsByServiceProvider(int providerId);
    ResponseDTO updateBookingStatus(int bookingId, String status);
    ResponseDTO updateHoursWorked(int bookingId, double hours);
    ResponseDTO cancelBooking(int bookingId);
    ResponseDTO getActiveBookings();
    ResponseDTO getBookingsByCustomer(
            int customerId,
            String status,
            LocalDate fromDate,
            LocalDate toDate);

    ResponseDTO getBookingsByServiceProvider(
            int providerId,
            String status,
            LocalDate fromDate,
            LocalDate toDate
    ) ;
}