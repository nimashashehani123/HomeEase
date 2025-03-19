package com.example.homeease.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceProviderDTO {

    @NotNull
    private Long providerId;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\+?[0-9\\-\\(\\)\\s]*$", message = "Invalid contact number format")
    private String contact;

    @NotBlank(message = "Availability is required")
    private String availability;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull
    private Double rate;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long verificationId;

    // Getters and Setters
}

