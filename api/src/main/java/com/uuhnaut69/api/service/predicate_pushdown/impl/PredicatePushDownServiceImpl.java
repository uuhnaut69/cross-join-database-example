package com.uuhnaut69.api.service.predicate_pushdown.impl;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.index.hash.HashIndex;
import com.googlecode.cqengine.index.unique.UniqueIndex;
import com.googlecode.cqengine.query.Query;
import com.uuhnaut69.api.document.Review;
import com.uuhnaut69.api.mapper.ProductMapper;
import com.uuhnaut69.api.model.Product;
import com.uuhnaut69.api.payload.response.ProductResponse;
import com.uuhnaut69.api.service.predicate_pushdown.PredicatePushDownService;
import com.uuhnaut69.api.service.product.ProductService;
import com.uuhnaut69.api.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import static com.googlecode.cqengine.query.QueryFactory.existsIn;

/**
 * @author uuhnaut
 * @project demo
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PredicatePushDownServiceImpl implements PredicatePushDownService {

    private final ProductService productService;

    private final ReviewService reviewService;

    private final ProductMapper productMapper;

    @Override
    public Set<ProductResponse> getProductDetail(BigDecimal price, int rating) {
        IndexedCollection<Product> products = new ConcurrentIndexedCollection<>();
        products.addIndex(UniqueIndex.onAttribute(Product.ID));
        IndexedCollection<Review> reviews = new ConcurrentIndexedCollection<>();
        reviews.addIndex(HashIndex.onAttribute(Review.PRODUCT_ID));

        products.addAll(productService.findAllByPriceGte(price));
        reviews.addAll(reviewService.findAllByRatingGte(rating));

        Query<Product> productWithReviewRs = existsIn(reviews, Product.ID, Review.PRODUCT_ID);
        Set<Product> resultSet = products.retrieve(productWithReviewRs).stream().collect(Collectors.toSet());
        return productMapper.toProductResponse(resultSet);
    }
}
