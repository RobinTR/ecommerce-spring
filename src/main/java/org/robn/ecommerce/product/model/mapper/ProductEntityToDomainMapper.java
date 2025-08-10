package org.robn.ecommerce.product.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.product.model.Product;
import org.robn.ecommerce.product.model.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductEntityToDomainMapper extends BaseMapper<ProductEntity, Product> {
}
