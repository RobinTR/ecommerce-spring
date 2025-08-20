package org.robn.ecommerce.cart.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.robn.ecommerce.common.util.validation.AtLeastOneUserTypePresent;

import java.util.UUID;

@Getter
@Setter
@AtLeastOneUserTypePresent
public class AddToCartRequest {

    @NotNull
    @Positive
    private Long productId;

    private UUID customerId;

    private UUID sessionId;

    @Positive
    private Integer quantity = 1;

}
