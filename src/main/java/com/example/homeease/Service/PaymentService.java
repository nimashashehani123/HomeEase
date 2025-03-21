package com.example.homeease.Service;

import com.example.homeease.Entity.Payment;
import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    List<Payment> getAllPayments();
    Payment getPaymentById(int id);
    Payment updatePayment(int id, Payment payment);
    void deletePayment(int id);
}