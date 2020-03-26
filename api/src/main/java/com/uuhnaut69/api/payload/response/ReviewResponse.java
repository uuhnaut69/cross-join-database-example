package com.uuhnaut69.api.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author uuhnaut
 * @project demo
 */
@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse extends AbstractResponse {

    private Long productId;

    private String reviewContent;

    private String author;

    private ReviewResponse parentReviewResponse;
}
