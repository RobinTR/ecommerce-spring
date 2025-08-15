package org.robn.ecommerce.productcategory.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.product.model.response.ProductListResponse;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryProductsResponse {

    private Long categoryId;
    private List<ProductListResponse> products;

}
