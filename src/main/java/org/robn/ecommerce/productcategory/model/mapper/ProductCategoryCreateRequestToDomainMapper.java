package org.robn.ecommerce.productcategory.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.common.mapper.BaseMapper;
import org.robn.ecommerce.productcategory.model.ProductCategory;
import org.robn.ecommerce.productcategory.model.request.ProductCategoryCreateRequest;

@Mapper(componentModel = "spring")
public interface ProductCategoryCreateRequestToDomainMapper extends BaseMapper<ProductCategoryCreateRequest, ProductCategory> {
}
