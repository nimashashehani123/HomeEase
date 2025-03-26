package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.BookingDTO;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Entity.Booking;
import com.example.homeease.Entity.Service;
import com.example.homeease.Entity.User;
import com.example.homeease.Repo.BookingRepository;
import com.example.homeease.Repo.ServiceRepository;
import com.example.homeease.Repo.UserRepository;
import com.example.homeease.Service.BookingService;
import com.example.homeease.Utill.VarList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseDTO createBooking(BookingDTO bookingDTO) {
        try {
            // Validate customer exists
            User customer = userRepository.findById(bookingDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            // Validate service exists
            Service service = serviceRepository.findById(bookingDTO.getServiceId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

            // Map DTO to Entity
            Booking booking = modelMapper.map(bookingDTO, Booking.class);
            booking.setCustomer(customer);
            booking.setService(service);
            booking.setStatus(bookingDTO.getStatus() != null ? bookingDTO.getStatus() : "PENDING");

            Booking savedBooking = bookingRepository.save(booking);
            BookingDTO responseDTO = modelMapper.map(savedBooking, BookingDTO.class);

            return new ResponseDTO(VarList.Created, "Booking created successfully", responseDTO);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Internal server error", null);
        }
    }

    @Override
    public ResponseDTO getBookingById(int bookingId) {
        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

            BookingDTO responseDTO = modelMapper.map(booking, BookingDTO.class);
            return new ResponseDTO(VarList.OK, "Booking retrieved successfully", responseDTO);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO getBookingsByCustomer(int customerId) {
        try {
            List<Booking> bookings = bookingRepository.findByCustomer_UserId(customerId);

            if (bookings.isEmpty()) {
                return new ResponseDTO(VarList.Not_Found, "No bookings found for this customer", null);
            }

            List<BookingDTO> responseDTOs = bookings.stream()
                    .map(booking -> modelMapper.map(booking, BookingDTO.class))
                    .collect(Collectors.toList());

            return new ResponseDTO(VarList.OK, "Bookings retrieved successfully", responseDTOs);

        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Error retrieving bookings", null);
        }
    }

    @Override
    public ResponseDTO getBookingsByService(int serviceId) {
        try {
            List<Booking> bookings = bookingRepository.findByService_ServiceId(serviceId);

            if (bookings.isEmpty()) {
                return new ResponseDTO(VarList.Not_Found, "No bookings found for this service", null);
            }

            List<BookingDTO> responseDTOs = bookings.stream()
                    .map(booking -> modelMapper.map(booking, BookingDTO.class))
                    .collect(Collectors.toList());

            return new ResponseDTO(VarList.OK, "Bookings retrieved successfully", responseDTOs);

        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Error retrieving bookings", null);
        }
    }

    @Override
    public ResponseDTO getBookingsByServiceProvider(int providerId) {
        try {
            List<Booking> bookings = bookingRepository.findByServiceProviderId(providerId);

            if (bookings.isEmpty()) {
                return new ResponseDTO(VarList.Not_Found, "No bookings found for this provider", null);
            }

            List<BookingDTO> responseDTOs = bookings.stream()
                    .map(booking -> modelMapper.map(booking, BookingDTO.class))
                    .collect(Collectors.toList());

            return new ResponseDTO(VarList.OK, "Bookings retrieved successfully", responseDTOs);

        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Error retrieving bookings", null);
        }
    }

    @Override
    @Transactional
    public ResponseDTO updateBookingStatus(int bookingId, String status) {
        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

            booking.setStatus(status);
            Booking updatedBooking = bookingRepository.save(booking);

            BookingDTO responseDTO = modelMapper.map(updatedBooking, BookingDTO.class);
            return new ResponseDTO(VarList.OK, "Booking status updated", responseDTO);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        }
    }

    @Override
    @Transactional
    public ResponseDTO updateHoursWorked(int bookingId, double hours) {
        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

            booking.setHoursWorked(hours);
            Booking updatedBooking = bookingRepository.save(booking);

            BookingDTO responseDTO = modelMapper.map(updatedBooking, BookingDTO.class);
            return new ResponseDTO(VarList.OK, "Hours worked updated", responseDTO);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        }
    }

    @Override
    @Transactional
    public ResponseDTO cancelBooking(int bookingId) {
        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

            if ("COMPLETED".equals(booking.getStatus())) {
                return new ResponseDTO(VarList.Bad_Request, "Cannot cancel completed booking", null);
            }

            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);

            return new ResponseDTO(VarList.OK, "Booking cancelled successfully", null);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO getActiveBookings() {
        try {
            List<Booking> activeBookings = bookingRepository.findByStatusIn(List.of("PENDING", "ACCEPTED"));

            if (activeBookings.isEmpty()) {
                return new ResponseDTO(VarList.Not_Found, "No active bookings found", null);
            }

            List<BookingDTO> responseDTOs = activeBookings.stream()
                    .map(booking -> modelMapper.map(booking, BookingDTO.class))
                    .collect(Collectors.toList());

            return new ResponseDTO(VarList.OK, "Active bookings retrieved", responseDTOs);

        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Error retrieving active bookings", null);
        }
    }


    @Override
    public ResponseDTO getBookingsByCustomer(
            int customerId,
            String status,
            LocalDate fromDate,
            LocalDate toDate) {

        try {
            // Validate customer exists
            if (!userRepository.existsById(customerId)) {
                throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
            }

            List<Booking> bookings = bookingRepository.findBookingsByCustomer(
                    customerId,
                    status,
                    fromDate,
                    toDate
            );

            return createResponseFromBookings(bookings);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error,
                    "Error retrieving customer bookings: " + ex.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO getBookingsByServiceProvider(
            int providerId,
            String status,
            LocalDate fromDate,
            LocalDate toDate) {

        try {
            // Validate provider exists
            if (!userRepository.existsById(providerId)) {
                throw new ResourceNotFoundException("Service provider not found with ID: " + providerId);
            }

            List<Booking> bookings = bookingRepository.findProviderBookings(
                    providerId,
                    status,
                    fromDate,
                    toDate
            );

            return createResponseFromBookings(bookings);

        } catch (ResourceNotFoundException ex) {
            return new ResponseDTO(VarList.Not_Found, ex.getMessage(), null);
        } catch (Exception ex) {
            return new ResponseDTO(VarList.Internal_Server_Error,
                    "Error retrieving provider bookings: " + ex.getMessage(), null);
        }
    }

    private ResponseDTO createResponseFromBookings(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            return new ResponseDTO(VarList.OK, "No bookings found", Collections.emptyList());
        }

        List<BookingDTO> bookingDTOs = bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());

        return new ResponseDTO(VarList.OK, "Bookings retrieved successfully", bookingDTOs);
    }
}