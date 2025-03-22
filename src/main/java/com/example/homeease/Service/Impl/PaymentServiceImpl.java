package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.PaymentDTO;
import com.example.homeease.Entity.Payment;
import com.example.homeease.Entity.Booking;
import com.example.homeease.Repo.PaymentRepository;
import com.example.homeease.Repo.BookingRepository;
import com.example.homeease.Service.PaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO createPayment(PaymentDTO paymentDTO) {
        if (paymentRepository.existsById(paymentDTO.getPaymentId())) {
            return new ResponseDTO(400, "Payment already exists with id: " + paymentDTO.getPaymentId(), null);
        }

        // Fetch the booking from the database
        Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + paymentDTO.getBookingId()));

        // Map PaymentDTO to Payment entity
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setBooking(booking); // Set the booking

        paymentRepository.save(payment);
        return new ResponseDTO(200, "Payment created successfully", paymentDTO);
    }

    @Override
    public ResponseDTO getAllPayments() {
        List<PaymentDTO> paymentList = modelMapper.map(paymentRepository.findAll(),
                new TypeToken<List<PaymentDTO>>() {}.getType());
        return new ResponseDTO(200, "Payments retrieved successfully", paymentList);
    }

    @Override
    public ResponseDTO getPaymentById(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));
        PaymentDTO paymentDTO = modelMapper.map(payment, PaymentDTO.class);
        paymentDTO.setBookingId(payment.getBooking().getBookingId()); // Set booking ID
        return new ResponseDTO(200, "Payment retrieved successfully", paymentDTO);
    }

    @Override
    public ResponseDTO updatePayment(int paymentId, PaymentDTO paymentDTO) {
        if (!paymentRepository.existsById(paymentId)) {
            return new ResponseDTO(404, "Payment not found with id: " + paymentId, null);
        }

        // Fetch the booking from the database
        Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + paymentDTO.getBookingId()));

        // Map PaymentDTO to Payment entity
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setPaymentId(paymentId); // Ensure the ID is preserved
        payment.setBooking(booking); // Set the booking

        paymentRepository.save(payment);
        return new ResponseDTO(200, "Payment updated successfully", paymentDTO);
    }

    @Override
    public ResponseDTO deletePayment(int paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            return new ResponseDTO(404, "Payment not found with id: " + paymentId, null);
        }
        paymentRepository.deleteById(paymentId);
        return new ResponseDTO(200, "Payment deleted successfully", null);
    }
}