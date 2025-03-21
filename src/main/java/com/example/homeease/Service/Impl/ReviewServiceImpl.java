package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Review;
import com.example.homeease.Repo.ReviewRepository;
import com.example.homeease.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review updateReview(int id, Review review) {
        Review existingReview = reviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            existingReview.setRating(review.getRating());
            existingReview.setComment(review.getComment());
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    @Override
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}