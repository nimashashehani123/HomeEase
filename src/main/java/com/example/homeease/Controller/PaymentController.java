package com.example.homeease.Controller;

import com.example.homeease.Entity.Payment;
import com.example.homeease.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable int id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
    }
}