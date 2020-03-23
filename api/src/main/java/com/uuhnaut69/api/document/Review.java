package com.uuhnaut69.api.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author uuhnaut
 * @project demo
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends AbstractDocument {

    private Long productId;

    private String reviewContent;

    private String author;

    private Long parentReviewId;
}
