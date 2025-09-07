package org.robn.ecommerce.productcategory.model.response;

import lombok.Builder;
import org.robn.ecommerce.category.model.response.CategoryListResponse;

import java.util.List;

@Builder
public record ProductCategoriesResponse(

        Long productId,
        List<CategoryListResponse> categories

) {
}
