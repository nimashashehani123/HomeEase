package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.PaymentDTO;
import com.example.homeease.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        ResponseDTO response = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllPayments() {
        ResponseDTO response = paymentService.getAllPayments();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<ResponseDTO> getPaymentById(@PathVariable int paymentId) {
        ResponseDTO response = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<ResponseDTO> updatePayment(@PathVariable int paymentId, @RequestBody PaymentDTO paymentDTO) {
        ResponseDTO response = paymentService.updatePayment(paymentId, paymentDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<ResponseDTO> deletePayment(@PathVariable int paymentId) {
        ResponseDTO response = paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}