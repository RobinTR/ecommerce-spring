package org.robn.ecommerce.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.request.ProductUpdateRequest;

@Mapper(componentModel = "spring")
public interface ProductUpdateMapper {

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Product product, ProductUpdateRequest request);

}
