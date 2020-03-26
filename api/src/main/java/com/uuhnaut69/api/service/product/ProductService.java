package com.uuhnaut69.api.service.product;

import com.uuhnaut69.api.model.Product;
import io.debezium.data.Envelope;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author uuhnaut
 * @project demo
 */
public interface ProductService {

    List<Product> dummyProductData();

    List<Product> findAll();

    void maintainReadModel(Map<String, Object> productData, Envelope.Operation operation);

    Set<Product> findAllByPriceGte(BigDecimal price);

}
