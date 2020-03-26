package com.uuhnaut69.api.mapper;

import com.uuhnaut69.api.model.Product;
import com.uuhnaut69.api.payload.response.ProductResponse;
import org.mapstruct.Mapper;

import java.util.Set;

/**
 * @author uuhnaut
 * @project demo
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    Set<ProductResponse> toProductResponse(Set<Product> products);
}
