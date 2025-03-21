package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(int id) throws ResourceNotFoundException;
    Booking updateBooking(int id, Booking booking) throws ResourceNotFoundException;
    void deleteBooking(int id) throws ResourceNotFoundException;
}