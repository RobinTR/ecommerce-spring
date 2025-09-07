package org.robn.ecommerce.productcategory.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCategoryCreateRequest(

        @NotNull
        @Positive
        Long productId,

        @NotNull
        @Positive
        Long categoryId

) {
}
