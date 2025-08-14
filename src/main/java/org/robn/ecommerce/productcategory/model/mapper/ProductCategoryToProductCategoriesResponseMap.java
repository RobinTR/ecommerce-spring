package org.robn.ecommerce.productcategory.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.robn.ecommerce.category.model.response.CategoryResponse;
import org.robn.ecommerce.productcategory.model.response.ProductCategoriesResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryToProductCategoriesResponseMap {

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "categories", source = "categories")
    ProductCategoriesResponse map(final Long productId, final List<CategoryResponse> categories);

}
