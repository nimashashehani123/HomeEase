package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ReviewDTO;
import com.example.homeease.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
        ResponseDTO response = reviewService.addReview(reviewDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllReviews() {
        ResponseDTO response = reviewService.getAllReviews();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO> getReviewById(@PathVariable int reviewId) {
        ResponseDTO response = reviewService.getReviewById(reviewId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO> updateReview(@PathVariable int reviewId, @RequestBody ReviewDTO reviewDTO) {
        ResponseDTO response = reviewService.updateReview(reviewId, reviewDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO> deleteReview(@PathVariable int reviewId) {
        ResponseDTO response = reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}