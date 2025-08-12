package org.robn.ecommerce.product.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.product.model.ProductImage;
import org.robn.ecommerce.product.model.request.ProductImageCreateRequest;

@Mapper(componentModel = "spring")
public interface ProductImageCreateRequestToDomainMapper extends BaseMapper<ProductImageCreateRequest, ProductImage> {
}
