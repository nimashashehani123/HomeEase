package com.example.homeease.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceDTO {

    @NotNull
    private Long serviceId;

    @NotBlank(message = "Image is required")
    private String image;

    @NotBlank(message = "Service type is required")
    private String type;

    @NotNull
    private Double upfrontCharge;

    @NotNull
    private Double emergencyCharge;

    @NotNull
    private Double chargePerHourForNormalService;

    @NotNull
    private Long providerId;

    @NotNull
    private Long categoryId;

    // Getters and Setters
}
