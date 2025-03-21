package com.example.homeease.Controller;
import com.example.homeease.Dto.ReviewDTO;
import com.example.homeease.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @DeleteMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/{reviewId}")
    public ReviewDTO getReviewById(@PathVariable int reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @GetMapping("/by-service/{serviceId}")
    public List<ReviewDTO> getReviewsByService(@PathVariable int serviceId) {
        return reviewService.getReviewsByService(serviceId);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<ReviewDTO> getReviewsByCustomer(@PathVariable int customerId) {
        return reviewService.getReviewsByCustomer(customerId);
    }
}