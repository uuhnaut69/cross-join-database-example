package com.uuhnaut69.api.service.product.impl;

import com.github.javafaker.Faker;
import com.uuhnaut69.api.model.Product;
import com.uuhnaut69.api.repository.ProductRepository;
import com.uuhnaut69.api.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author uuhnaut
 * @project demo
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

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
}
