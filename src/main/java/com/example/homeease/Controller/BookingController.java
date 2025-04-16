package com.example.homeease.Controller;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.*;
import com.example.homeease.Entity.Booking;
import com.example.homeease.Entity.Service;
import com.example.homeease.Entity.User;
import com.example.homeease.Service.BookingService;
import com.example.homeease.Service.Impl.EmailService;
import com.example.homeease.Service.ServiceService;
import com.example.homeease.Service.UserService;
import com.example.homeease.Utill.VarList;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<ResponseDTO> createBooking(
            @Valid @RequestBody BookingDTO bookingDTO) {

        try {
            // 1. Create booking
            ResponseDTO response = bookingService.createBooking(bookingDTO);

            // 2. Only send email if booking was successful
            if (response.getCode() == VarList.Created) {
                        try {
                            // Get the created booking from response
                            BookingDTO booking = (BookingDTO) response.getData();
                            UserDTO customer = null;
                            // Fetch customer details by customerId
                            ResponseDTO customerResponse = userService.getUserById(booking.getCustomerId());
                            if (customerResponse.getCode() == 200) {
                               customer  = (UserDTO) customerResponse.getData();

                            }
                            ServiceDTO service = null;
                            ResponseDTO serviceResponse = serviceService.getServiceById(booking.getServiceId());
                            if (serviceResponse.getCode() == 200) {
                               service  = (ServiceDTO) serviceResponse.getData();

                            }


                            System.out.println(service);
                                sendBookingConfirmationEmail(booking, customer,service);
                } catch (MessagingException e) {
                    // Log email failure but don't fail the request
                    System.err.println("Email sending failed: " + e.getMessage());
                    // You could add this to the response message if needed
                    response.setMessage(response.getMessage() + " (Note: Confirmation email failed to send)");
                }
            }

            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    private void sendBookingConfirmationEmail(BookingDTO bookingDTO ,UserDTO user,ServiceDTO service) throws MessagingException {
        String emailContent = buildEmailContent(bookingDTO,user,service);

        EmailDTO emailDto = new EmailDTO();
        emailDto.setTo(user.getEmail());
        emailDto.setSubject("Booking Confirmation #" + bookingDTO.getBookingId());
        emailDto.setContent(emailContent);
        emailService.sendEmail(emailDto);
    }

    private String buildEmailContent(BookingDTO dto,UserDTO user,ServiceDTO service) {
        return "<!DOCTYPE html>" +
                "<html><head><style>" +
                "body { font-family: Arial, sans-serif; line-height: 1.6; }" +
                "h2 { color: #2c3e50; }" +
                "table { border-collapse: collapse; width: 100%; max-width: 500px; }" +
                "td { padding: 8px; border-bottom: 1px solid #ddd; }" +
                "td:first-child { font-weight: bold; width: 30%; }" +
                "</style></head>" +
                "<body>" +
                "<h2>Your Booking is Confirmed!</h2>" +
                "<p>Dear " + user.getName() + ",</p>" +
                "<p>Thank you for your booking with HomeEase. Here are your details:</p>" +
                "<table>" +
                "<tr><td>Booking ID:</td><td>" + dto.getBookingId() + "</td></tr>" +
                "<tr><td>Service:</td><td>" + service.getServiceName() + "</td></tr>" +
                "<tr><td>Date:</td><td>" + dto.getBookingDateTime() + "</td></tr>" +
                "<tr><td>Status:</td><td>" + dto.getStatus() + "</td></tr>" +
                "</table>" +
                "<p>You can view or manage your booking by logging into your account.</p>" +
                "<p>Thank you for choosing HomeEase!</p>" +
                "</body></html>";
    }


    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getBookingsForCustomer(@Valid
            @PathVariable int id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            ResponseDTO responseDTO = bookingService.getBookingsForCustomer(id, status, fromDate, toDate);
            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", responseDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error: " + e.getMessage(), null));
        }
    }

    @GetMapping("/provider/{id}")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<?> getBookingsForProvider(@Valid
            @PathVariable int id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            ResponseDTO responseDTO = bookingService.getBookingsForProvider(id, status, fromDate, toDate);
            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", responseDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error: " + e.getMessage(), null));
        }
    }



    @GetMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> getBookingById(@Valid @PathVariable int bookingId) {
        try {
            ResponseDTO response = bookingService.getBookingById(bookingId);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ResponseDTO> getBookingsByService(@Valid @PathVariable int serviceId) {
        System.out.println("fgdhfhhdh" + serviceId);
        try {
            ResponseDTO responseDTO = bookingService.getBookingsByService(serviceId);
            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", responseDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }


    @PatchMapping("/{bookingId}/status")
    public ResponseEntity<ResponseDTO> updateBookingStatus(@Valid
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

    @PatchMapping("/{bookingId}/status/duration")
    @PreAuthorize("hasAuthority('SERVICE_PROVIDER')")
    public ResponseEntity<ResponseDTO> updateHoursWorked(@Valid
            @PathVariable int bookingId,
            @RequestParam String status,
            @RequestParam double duration) {
        try {
            ResponseDTO response = bookingService.updateHoursWorked(bookingId, duration,status);
            return ResponseEntity.status(HttpStatus.valueOf(response.getCode()))
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ResponseDTO> cancelBooking(@Valid @PathVariable int bookingId) {
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