package com.uuhnaut69.api.payload;

import lombok.Getter;
import lombok.Setter;

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

    private Long parentReviewId;
}
