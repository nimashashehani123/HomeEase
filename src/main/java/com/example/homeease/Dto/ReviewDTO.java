package com.example.homeease.Dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewId;
    private int bookingId; // Associated booking ID
    private int rating; // Rating (1-5)
    private String comment; // Review comment
}