package org.robn.ecommerce.cart.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddToCartRequest(

        @NotNull
        @Positive
        Long productId,

        @Positive
        Integer quantity

) {

    public AddToCartRequest {
        quantity = (quantity == null) ? 1 : quantity;
    }

}
