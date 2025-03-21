package com.example.homeease.Dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewId; // Unique identifier for the review
    private int bookingId; // ID of the associated booking
    private int rating; // Rating (1-5)
    private String comment; // Review comment
}