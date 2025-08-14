package org.robn.ecommerce.productcategory.model.response;

import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.product.model.response.ProductResponse;

import java.util.List;

@Getter
@Setter
public class CategoryProductsResponse {

    private Long categoryId;
    private List<ProductResponse> products;

}
