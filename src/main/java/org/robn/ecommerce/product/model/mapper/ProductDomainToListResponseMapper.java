package org.robn.ecommerce.product.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.response.ProductListResponse;

@Mapper(componentModel = "spring")
public interface ProductDomainToListResponseMapper extends BaseMapper<Product, ProductListResponse> {
}
