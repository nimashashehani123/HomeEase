package com.example.homeease.Service;
import com.example.homeease.Dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    void deletePayment(int paymentId);
    PaymentDTO getPaymentById(int paymentId);
    List<PaymentDTO> getPaymentsByBooking(int bookingId);
    List<PaymentDTO> getPaymentsByStatus(String status);
}
