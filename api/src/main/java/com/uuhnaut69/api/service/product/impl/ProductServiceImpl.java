package com.uuhnaut69.api.service.product.impl;

import com.github.javafaker.Faker;
import com.uuhnaut69.api.model.Product;
import com.uuhnaut69.api.repository.ProductRepository;
import com.uuhnaut69.api.repository.ReviewRepository;
import com.uuhnaut69.api.service.product.ProductService;
import io.debezium.data.Envelope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author uuhnaut
 * @project demo
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    @Override
    public List<Product> dummyProductData() {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> products.add(Product
                .builder()
                .productName(faker.commerce().productName())
                .madeIn(faker.country().name())
                .material(faker.commerce().material())
                .price(new BigDecimal(faker.commerce().price())).build()));
        return productRepository.saveAll(products);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void maintainReadModel(Map<String, Object> productData, Envelope.Operation operation) {
        if (Envelope.Operation.DELETE.name().equals(operation.name())) {
            reviewRepository.deleteAllByProductId(Integer.parseInt(productData.get("id").toString()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Product> findAllByPriceGte(BigDecimal price) {
        return productRepository.findAllByPriceGreaterThanEqual(price);
    }
}
