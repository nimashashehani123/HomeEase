package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.PaymentDTO;

public interface PaymentService {
    ResponseDTO createPayment(PaymentDTO paymentDTO);
    ResponseDTO getAllPayments();
    ResponseDTO getPaymentById(int paymentId);
    ResponseDTO updatePayment(int paymentId, PaymentDTO paymentDTO);
    ResponseDTO deletePayment(int paymentId);
}