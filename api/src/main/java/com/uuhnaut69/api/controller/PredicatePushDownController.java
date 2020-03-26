package com.uuhnaut69.api.controller;

import com.uuhnaut69.api.payload.response.ProductResponse;
import com.uuhnaut69.api.service.predicate_pushdown.PredicatePushDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author uuhnaut
 * @project demo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class PredicatePushDownController {

    private final PredicatePushDownService predicatePushDownService;

    /**
     * Perform predicate push down query example
     * <p>
     * "SELECT * FROM mysql.product A JOIN mongodb.review B
     * WHERE A.price > price AND B.rating > rating"
     *
     * @param price
     * @return Set {@link ProductResponse}
     */
    @GetMapping("/query-1")
    public Set<ProductResponse> getProductDetail(@RequestParam(value = "price", defaultValue = "0") BigDecimal price,
                                                 @RequestParam(value = "rating", defaultValue = "0") int rating) {
        return predicatePushDownService.getProductDetail(price, rating);
    }
}
