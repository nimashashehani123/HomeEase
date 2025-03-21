package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Payment;
import com.example.homeease.Repo.PaymentRepository;
import com.example.homeease.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment updatePayment(int id, Payment payment) {
        Payment existingPayment = paymentRepository.findById(id).orElse(null);
        if (existingPayment != null) {
            existingPayment.setDepositAmount(payment.getDepositAmount());
            existingPayment.setFinalAmount(payment.getFinalAmount());
            existingPayment.setDepositTransactionId(payment.getDepositTransactionId());
            existingPayment.setFinalTransactionId(payment.getFinalTransactionId());
            existingPayment.setStatus(payment.getStatus());
            return paymentRepository.save(existingPayment);
        }
        return null;
    }

    @Override
    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }
}