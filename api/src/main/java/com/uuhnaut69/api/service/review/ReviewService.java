package com.uuhnaut69.api.service.review;

import com.uuhnaut69.api.document.Review;
import com.uuhnaut69.api.payload.ReviewRequest;

/**
 * @author uuhnaut
 * @project demo
 */
public interface ReviewService {

    Review findById(String id);

    Review create(Long productId, ReviewRequest reviewRequest);

    Review update(String reviewId, ReviewRequest reviewRequest);

    void delete(String reviewId);

}
