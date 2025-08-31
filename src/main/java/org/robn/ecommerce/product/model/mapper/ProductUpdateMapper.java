package org.robn.ecommerce.product.model.mapper;

import org.mapstruct.*;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.request.ProductUpdateRequest;

@Mapper(componentModel = "spring")
public interface ProductUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Product product, ProductUpdateRequest request);

}
