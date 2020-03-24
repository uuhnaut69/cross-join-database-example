package com.uuhnaut69.api.controller;

import com.uuhnaut69.api.document.Review;
import com.uuhnaut69.api.payload.ReviewRequest;
import com.uuhnaut69.api.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/{productId}/reviews")
    public Page<Review> findCommentsOfProduct(@PathVariable Long productId,
                                              @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy,
                                              @RequestParam(value = "order", defaultValue = "desc") String order,
                                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Sort sort = "asc".equalsIgnoreCase(order) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return reviewService.findCommentsOfProduct(pageable, productId);
    }

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
