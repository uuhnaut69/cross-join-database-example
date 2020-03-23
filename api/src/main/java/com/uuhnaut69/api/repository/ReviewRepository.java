package com.uuhnaut69.api.repository;

import com.uuhnaut69.api.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author uuhnaut
 * @project demo
 */
@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
}
