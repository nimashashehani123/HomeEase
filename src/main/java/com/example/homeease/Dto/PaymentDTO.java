package com.example.homeease.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    @NotNull
    private Long paymentId;

    @NotNull
    private Double amount;

    @NotNull(message = "Payment date is required")
    private LocalDateTime paymentDate;

    @NotBlank(message = "Payment type is required")
    private String paymentType;

    private Long bookingId;
    private Long requestId;

    // Getters and Setters
}
