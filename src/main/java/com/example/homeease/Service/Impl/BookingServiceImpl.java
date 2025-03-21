package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Booking;
import com.example.homeease.Repo.BookingRepository;
import com.example.homeease.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking updateBooking(int id, Booking booking) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            existingBooking.setBookingDateTime(booking.getBookingDateTime());
            existingBooking.setStatus(booking.getStatus());
            existingBooking.setHoursWorked(booking.getHoursWorked());
            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    @Override
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}