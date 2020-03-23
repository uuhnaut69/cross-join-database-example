package com.uuhnaut69.api.repository;

import com.uuhnaut69.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author uuhnaut
 * @project demo
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
