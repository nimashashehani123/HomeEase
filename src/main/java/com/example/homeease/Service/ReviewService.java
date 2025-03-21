package com.example.homeease.Service;

import com.example.homeease.Entity.Review;
import java.util.List;

public interface ReviewService {
    Review addReview(Review review);
    List<Review> getAllReviews();
    Review getReviewById(int id);
    Review updateReview(int id, Review review);
    void deleteReview(int id);
}