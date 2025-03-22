package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.BookingDTO;

public interface BookingService {
    ResponseDTO createBooking(BookingDTO bookingDTO);
    ResponseDTO getAllBookings();
    ResponseDTO getBookingById(int bookingId);
    ResponseDTO updateBooking(int bookingId, BookingDTO bookingDTO);
    ResponseDTO deleteBooking(int bookingId);
}