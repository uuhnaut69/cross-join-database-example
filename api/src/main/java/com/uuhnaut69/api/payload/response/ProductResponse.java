package com.uuhnaut69.api.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author uuhnaut
 * @project demo
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductResponse extends AbstractResponse {

    private String productName;

    private String madeIn;

    private String material;

    private BigDecimal price;

    private List<ReviewResponse> reviewResponseList;
}
