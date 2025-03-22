package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        ResponseDTO response = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllBookings() {
        ResponseDTO response = bookingService.getAllBookings();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> getBookingById(@PathVariable int bookingId) {
        ResponseDTO response = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> updateBooking(@PathVariable int bookingId, @RequestBody BookingDTO bookingDTO) {
        ResponseDTO response = bookingService.updateBooking(bookingId, bookingDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable int bookingId) {
        ResponseDTO response = bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}