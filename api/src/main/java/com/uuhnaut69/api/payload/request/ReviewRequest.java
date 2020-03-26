package com.uuhnaut69.api.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author uuhnaut
 * @project demo
 */
@Getter
@Setter
public class ReviewRequest {

    @NotNull
    private String reviewContent;

    @NotNull
    private String author;

    private String parentReviewId;

    @Min(0)
    @Max(5)
    private int rating;
}
