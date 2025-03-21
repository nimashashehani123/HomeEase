package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review);
    List<Review> getAllReviews();
    Review getReviewById(int id) throws ResourceNotFoundException;
    Review updateReview(int id, Review review) throws ResourceNotFoundException;
    void deleteReview(int id) throws ResourceNotFoundException;
}