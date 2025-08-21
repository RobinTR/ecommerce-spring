package org.robn.ecommerce.cart.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {

    @NotNull
    @Positive
    private Long productId;

    @Positive
    private Integer quantity = 1;

}
