package com.example.homeease.Service;
import com.example.homeease.Dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBookingStatus(int bookingId, String status);
    void deleteBooking(int bookingId);
    BookingDTO getBookingById(int bookingId);
    List<BookingDTO> getBookingsByCustomer(int customerId);
    List<BookingDTO> getBookingsByProvider(int providerId);
}