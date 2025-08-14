package org.robn.ecommerce.productcategory.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.productcategory.model.ProductCategory;
import org.robn.ecommerce.productcategory.model.entity.ProductCategoryEntity;

@Mapper(componentModel = "spring")
public interface ProductCategoryEntityToDomainMapper extends BaseMapper<ProductCategoryEntity, ProductCategory> {
}
