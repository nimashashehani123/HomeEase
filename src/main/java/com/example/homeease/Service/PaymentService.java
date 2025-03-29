package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.PaymentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PaymentService {
    ResponseDTO createPayment(PaymentDTO paymentDTO);
    ResponseDTO getAllPayments();
    ResponseDTO getPaymentById(int paymentId);
    ResponseDTO getPaymentByBookingId(int bookingId);
    ResponseDTO updatePayment(int paymentId, PaymentDTO paymentDTO);
    ResponseDTO deletePayment(int paymentId);
    ResponseDTO updatePaymentDetails(int paymentId, Double finalAmount, String  status, LocalDateTime paymentDate);
}