package com.example.homeease.Controller;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Service.BookingService;
import com.example.homeease.Utill.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<ResponseDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            ResponseDTO response = bookingService.createBooking(bookingDTO);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ResponseDTO> getCustomerBookings(
            @PathVariable Integer customerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        try {
            ResponseDTO responseDTO = bookingService.getBookingsByCustomer(
                    customerId,
                    status,
                    fromDate,
                    toDate
            );

            return ResponseEntity.ok(
                    new ResponseDTO(
                            VarList.OK,
                            "Bookings retrieved successfully",
                            responseDTO
                    )
            );
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, ex.getMessage(), null));
        }
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<ResponseDTO> getProviderBookings(
            @PathVariable Integer providerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        try {
            ResponseDTO responseDTO = bookingService.getBookingsByServiceProvider(
                    providerId,
                    status,
                    fromDate,
                    toDate
            );

            return ResponseEntity.ok(
                    new ResponseDTO(
                            VarList.OK,
                            "Bookings retrieved successfully",
                            responseDTO
                    )
            );
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, ex.getMessage(), null));
        }
    }







    @GetMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> getBookingById(@PathVariable int bookingId) {
        try {
            ResponseDTO response = bookingService.getBookingById(bookingId);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ResponseDTO> getBookingsByCustomer(@PathVariable int customerId) {
        try {
            ResponseDTO response = bookingService.getBookingsByCustomer(customerId);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ResponseDTO> getBookingsByService(@PathVariable int serviceId) {
        try {
            ResponseDTO response = bookingService.getBookingsByService(serviceId);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/provider/{providerId}")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> getBookingsByProvider(@PathVariable int providerId) {
        try {
            ResponseDTO response = bookingService.getBookingsByServiceProvider(providerId);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PatchMapping("/{bookingId}/status")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> updateBookingStatus(
            @PathVariable int bookingId,
            @RequestParam String status) {
        try {
            ResponseDTO response = bookingService.updateBookingStatus(bookingId, status);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PatchMapping("/{bookingId}/hours")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> updateHoursWorked(
            @PathVariable int bookingId,
            @RequestParam double hours) {
        try {
            ResponseDTO response = bookingService.updateHoursWorked(bookingId, hours);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ResponseDTO> cancelBooking(@PathVariable int bookingId) {
        try {
            ResponseDTO response = bookingService.cancelBooking(bookingId);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/active")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> getActiveBookings() {
        try {
            ResponseDTO response = bookingService.getActiveBookings();
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}