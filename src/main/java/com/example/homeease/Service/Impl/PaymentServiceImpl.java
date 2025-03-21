package com.example.homeease.Service.Impl;
import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.PaymentDTO;
import com.example.homeease.Entity.Payment;
import com.example.homeease.Repo.PaymentRepository;
import com.example.homeease.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        // Set booking, amount, currency, payment method, transaction ID, status, and payment date
        Payment savedPayment = paymentRepository.save(payment);
        return convertToPaymentDTO(savedPayment);
    }

    @Override
    public void deletePayment(int paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public PaymentDTO getPaymentById(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return convertToPaymentDTO(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByBooking(int bookingId) {
        return paymentRepository.findByBooking_BookingId(bookingId).stream()
                .map(this::convertToPaymentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status).stream()
                .map(this::convertToPaymentDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO convertToPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setBookingId(payment.getBooking().getBookingId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setTransactionId(payment.getTransactionId());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        return paymentDTO;
    }
}