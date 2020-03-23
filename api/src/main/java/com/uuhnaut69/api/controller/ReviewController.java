package com.uuhnaut69.api.controller;

import com.uuhnaut69.api.document.Review;
import com.uuhnaut69.api.payload.ReviewRequest;
import com.uuhnaut69.api.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author uuhnaut
 * @project demo
 */
@SuppressWarnings("MVCPathVariableInspection")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{productId}/reviews")
    public Review createReview(@PathVariable Long productId, @RequestBody @Valid ReviewRequest reviewRequest) {
        return reviewService.create(productId, reviewRequest);
    }

    @PutMapping("/{productId}/reviews/{reviewId}")
    public Review updateReview(@PathVariable String reviewId, @RequestBody @Valid ReviewRequest reviewRequest) {
        return reviewService.update(reviewId, reviewRequest);
    }

    @DeleteMapping("/{productId}/reviews/{reviewId}")
    public void deleteReview(@PathVariable String reviewId) {
        reviewService.delete(reviewId);
    }
}
