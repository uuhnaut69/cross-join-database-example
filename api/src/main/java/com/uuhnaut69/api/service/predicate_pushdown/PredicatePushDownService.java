package com.uuhnaut69.api.service.predicate_pushdown;

import com.uuhnaut69.api.payload.response.ProductResponse;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author uuhnaut
 * @project demo
 */
public interface PredicatePushDownService {

    Set<ProductResponse> getProductDetail(BigDecimal price, int rating);

}
