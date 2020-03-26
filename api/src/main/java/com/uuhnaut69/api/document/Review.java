package com.uuhnaut69.api.document;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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

    public static final Attribute<Review, Long> PRODUCT_ID = new SimpleAttribute<Review, Long>("productId") {
        @Override
        public Long getValue(Review r, QueryOptions queryOptions) {
            return r.productId;
        }
    };

    @DBRef
    private Review parenReview;
    @Min(0)
    @Max(5)
    private int rating;
}
