package com.example.homeease.Service;

import com.example.homeease.Entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(int id);
    Booking updateBooking(int id, Booking booking);
    void deleteBooking(int id);
}