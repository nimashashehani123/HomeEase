package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
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
    public Payment getPaymentById(int id) throws ResourceNotFoundException {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
    }

    @Override
    public Payment updatePayment(int id, Payment payment) throws ResourceNotFoundException {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        // Update the existing payment with new details
        existingPayment.setDepositAmount(payment.getDepositAmount());
        existingPayment.setFinalAmount(payment.getFinalAmount());
        existingPayment.setDepositTransactionId(payment.getDepositTransactionId());
        existingPayment.setFinalTransactionId(payment.getFinalTransactionId());
        existingPayment.setStatus(payment.getStatus());

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(int id) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        paymentRepository.delete(payment);
    }
}