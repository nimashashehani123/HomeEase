package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Entity.Booking;
import com.example.homeease.Entity.User;
import com.example.homeease.Entity.Service;
import com.example.homeease.Repo.BookingRepository;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Repo.ServiceRepository;
import com.example.homeease.Service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO createBooking(BookingDTO bookingDTO) {
        if (bookingRepository.existsById(bookingDTO.getBookingId())) {
            return new ResponseDTO(400, "Booking already exists with id: " + bookingDTO.getBookingId(), null);
        }

        // Fetch the customer (User) from the database
        User customer = userRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + bookingDTO.getCustomerId()));

        // Fetch the service from the database
        Service service = serviceRepository.findById(bookingDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + bookingDTO.getServiceId()));

        // Map BookingDTO to Booking entity
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setCustomer(customer); // Set the customer
        booking.setService(service); // Set the service

        bookingRepository.save(booking);
        return new ResponseDTO(200, "Booking created successfully", bookingDTO);
    }

    @Override
    public ResponseDTO getAllBookings() {
        List<BookingDTO> bookingList = modelMapper.map(bookingRepository.findAll(),
                new TypeToken<List<BookingDTO>>() {}.getType());
        return new ResponseDTO(200, "Bookings retrieved successfully", bookingList);
    }

    @Override
    public ResponseDTO getBookingById(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        bookingDTO.setCustomerId(booking.getCustomer().getUserId()); // Set customer ID
        bookingDTO.setServiceId(booking.getService().getServiceId()); // Set service ID
        return new ResponseDTO(200, "Booking retrieved successfully", bookingDTO);
    }

    @Override
    public ResponseDTO updateBooking(int bookingId, BookingDTO bookingDTO) {
        if (!bookingRepository.existsById(bookingId)) {
            return new ResponseDTO(404, "Booking not found with id: " + bookingId, null);
        }

        // Fetch the customer (User) from the database
        User customer = userRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + bookingDTO.getCustomerId()));

        // Fetch the service from the database
        Service service = serviceRepository.findById(bookingDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + bookingDTO.getServiceId()));

        // Map BookingDTO to Booking entity
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setBookingId(bookingId); // Ensure the ID is preserved
        booking.setCustomer(customer); // Set the customer
        booking.setService(service); // Set the service

        bookingRepository.save(booking);
        return new ResponseDTO(200, "Booking updated successfully", bookingDTO);
    }

    @Override
    public ResponseDTO deleteBooking(int bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            return new ResponseDTO(404, "Booking not found with id: " + bookingId, null);
        }
        bookingRepository.deleteById(bookingId);
        return new ResponseDTO(200, "Booking deleted successfully", null);
    }
}