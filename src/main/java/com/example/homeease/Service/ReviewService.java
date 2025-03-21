package com.example.homeease.Service;
import com.example.homeease.Dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO addReview(ReviewDTO reviewDTO);
    void deleteReview(int reviewId);
    ReviewDTO getReviewById(int reviewId);
    List<ReviewDTO> getReviewsByService(int serviceId);
    List<ReviewDTO> getReviewsByCustomer(int customerId);
}