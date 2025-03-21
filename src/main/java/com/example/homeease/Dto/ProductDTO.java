package com.example.homeease.Dto;

import lombok.Data;

@Data
public class ProductDTO {

    private int productId; // Unique identifier for the product

    private String productName; // Name of the product

    private String description; // Description of the product

    private double price; // Price of the product

    private String image; // Optional field for product image

    private String contactNumber; // Contact number for inquiries
}