package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    List<Payment> getAllPayments();
    Payment getPaymentById(int id) throws ResourceNotFoundException;
    Payment updatePayment(int id, Payment payment) throws ResourceNotFoundException;
    void deletePayment(int id) throws ResourceNotFoundException;
}