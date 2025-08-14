package org.robn.ecommerce.productcategory.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryCreateRequest {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @Positive
    private Long categoryId;

}
