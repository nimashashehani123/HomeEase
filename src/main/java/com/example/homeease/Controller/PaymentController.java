package com.example.homeease.Controller;

import com.example.homeease.Dto.PaymentDTO;
import com.example.homeease.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public PaymentDTO createPayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.createPayment(paymentDTO);
    }

    @DeleteMapping("/delete/{paymentId}")
    public void deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
    }

    @GetMapping("/{paymentId}")
    public PaymentDTO getPaymentById(@PathVariable int paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/by-booking/{bookingId}")
    public List<PaymentDTO> getPaymentsByBooking(@PathVariable int bookingId) {
        return paymentService.getPaymentsByBooking(bookingId);
    }

    @GetMapping("/by-status/{status}")
    public List<PaymentDTO> getPaymentsByStatus(@PathVariable String status) {
        return paymentService.getPaymentsByStatus(status);
    }
}