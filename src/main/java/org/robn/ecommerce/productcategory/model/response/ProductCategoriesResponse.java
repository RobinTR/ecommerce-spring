package org.robn.ecommerce.productcategory.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.category.model.response.CategoryResponse;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductCategoriesResponse {

    private Long productId;
    private List<CategoryResponse> categories;

}
