package org.robn.ecommerce.product.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.product.model.ProductImage;
import org.robn.ecommerce.product.model.response.ProductImageResponse;

@Mapper(componentModel = "spring")
public interface ProductImageDomainToResponseMapper extends BaseMapper<ProductImage, ProductImageResponse> {
}
