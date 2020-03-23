package com.uuhnaut69.api.service.review.impl;

import com.uuhnaut69.api.document.Review;
import com.uuhnaut69.api.exception.NotFoundException;
import com.uuhnaut69.api.payload.ReviewRequest;
import com.uuhnaut69.api.repository.ReviewRepository;
import com.uuhnaut69.api.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author uuhnaut
 * @project demo
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional(readOnly = true)
    public Review findById(String id) {
        return reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found review's " + id));
    }

    @Override
    public Review create(Long productId, ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setProductId(productId);
        return save(review, reviewRequest);
    }

    @Override
    public Review update(String reviewId, ReviewRequest reviewRequest) {
        Review review = findById(reviewId);
        return save(review, reviewRequest);
    }

    @Override
    public void delete(String reviewId) {
        Review review = findById(reviewId);
        reviewRepository.delete(review);
    }

    private Review save(Review review, ReviewRequest reviewRequest) {
        review.setAuthor(reviewRequest.getAuthor());
        review.setReviewContent(reviewRequest.getReviewContent());
        if (reviewRequest.getParentReviewId() != null) {
            review.setParentReviewId(reviewRequest.getParentReviewId());
        }
        return reviewRepository.save(review);
    }
}
