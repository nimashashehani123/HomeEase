package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ReviewDTO;

public interface ReviewService {
    ResponseDTO addReview(ReviewDTO reviewDTO);
    ResponseDTO getReviewByBookingId(int bookingId);
    ResponseDTO getAllReviews();
    ResponseDTO getReviewById(int reviewId);
    ResponseDTO updateReview(int reviewId, ReviewDTO reviewDTO);
    ResponseDTO deleteReview(int reviewId);
}