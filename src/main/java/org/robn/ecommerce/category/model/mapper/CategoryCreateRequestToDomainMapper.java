package org.robn.ecommerce.category.model.mapper;

import org.mapstruct.Mapper;
import org.robn.ecommerce.category.model.Category;
import org.robn.ecommerce.category.model.request.CategoryCreateRequest;
import org.robn.ecommerce.common.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface CategoryCreateRequestToDomainMapper extends BaseMapper<CategoryCreateRequest, Category> {
}
