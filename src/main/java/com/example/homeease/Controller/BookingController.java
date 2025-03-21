package com.example.homeease.Controller;

import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @PutMapping("/update-status/{bookingId}")
    public BookingDTO updateBookingStatus(@PathVariable int bookingId, @RequestParam String status) {
        return bookingService.updateBookingStatus(bookingId, status);
    }

    @DeleteMapping("/delete/{bookingId}")
    public void deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @GetMapping("/{bookingId}")
    public BookingDTO getBookingById(@PathVariable int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<BookingDTO> getBookingsByCustomer(@PathVariable int customerId) {
        return bookingService.getBookingsByCustomer(customerId);
    }

    @GetMapping("/by-provider/{providerId}")
    public List<BookingDTO> getBookingsByProvider(@PathVariable int providerId) {
        return bookingService.getBookingsByProvider(providerId);
    }
}