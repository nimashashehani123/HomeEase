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
public class ReviewDTO {

    @NotNull
    private Long reviewId;

    @NotBlank(message = "Comment is required")
    private String comment;

    @NotNull(message = "Review date is required")
    private LocalDateTime reviewDate;

    @NotNull
    private Long customerId;

    @NotNull
    private Long serviceId;

    // Getters and Setters
}
