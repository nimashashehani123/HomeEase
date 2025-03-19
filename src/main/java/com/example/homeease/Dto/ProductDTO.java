package com.example.homeease.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    @NotNull
    private Long productId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Stock availability is required")
    private Boolean stockAvailability;

    @NotBlank(message = "Contact is required")
    private String contact;

    @NotBlank(message = "image is required")
    private String image;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long providerId;

    // Getters and Setters
}
