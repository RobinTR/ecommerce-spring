package org.robn.ecommerce.productcategory.model.response;

import lombok.Builder;
import org.robn.ecommerce.product.model.response.ProductListResponse;

import java.util.List;

@Builder
public record CategoryProductsResponse(

        Long categoryId,
        List<ProductListResponse> products

) {
}
