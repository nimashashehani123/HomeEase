package com.example.homeease.Service.Impl;
import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ReviewDTO;
import com.example.homeease.Entity.Review;
import com.example.homeease.Repo.ReviewRepository;
import com.example.homeease.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        // Set booking, rating, and comment
        Review savedReview = reviewRepository.save(review);
        return convertToReviewDTO(savedReview);
    }

    @Override
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewDTO getReviewById(int reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        return convertToReviewDTO(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByService(int serviceId) {
        return reviewRepository.findByBooking_Service_ServiceId(serviceId).stream()
                .map(this::convertToReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByCustomer(int customerId) {
        return reviewRepository.findByBooking_Customer_UserId(customerId).stream()
                .map(this::convertToReviewDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO convertToReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewId(review.getReviewId());
        reviewDTO.setBookingId(review.getBooking().getBookingId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setComment(review.getComment());
        return reviewDTO;
    }
}