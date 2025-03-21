package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Entity.Booking;
import com.example.homeease.Repo.BookingRepository;
import com.example.homeease.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        // Set customer, service, booking date, and status
        booking.setStatus("Pending");
        Booking savedBooking = bookingRepository.save(booking);
        return convertToBookingDTO(savedBooking);
    }

    @Override
    public BookingDTO updateBookingStatus(int bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        booking.setStatus(status);
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToBookingDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public BookingDTO getBookingById(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return convertToBookingDTO(booking);
    }

    @Override
    public List<BookingDTO> getBookingsByCustomer(int customerId) {
        return bookingRepository.findByCustomer_UserId(customerId).stream()
                .map(this::convertToBookingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByProvider(int providerId) {
        return bookingRepository.findByService_ServiceProvider_UserId(providerId).stream()
                .map(this::convertToBookingDTO)
                .collect(Collectors.toList());
    }

    private BookingDTO convertToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setCustomerId(booking.getCustomer().getUserId());
        bookingDTO.setServiceId(booking.getService().getServiceId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setStatus(booking.getStatus());
        return bookingDTO;
    }
}