package com.example.homeease.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationDTO {

    @NotNull
    private Long verificationId;

    @NotBlank(message = "Document is required")
    private String document;

    @NotNull
    @Pattern(regexp = "^[0-9]{9}$", message = "Invalid identity card number format")
    private String identityCardNumber;

    @NotNull(message = "Verification status is required")
    private Boolean isVerified;

    // Getters and Setters
}

