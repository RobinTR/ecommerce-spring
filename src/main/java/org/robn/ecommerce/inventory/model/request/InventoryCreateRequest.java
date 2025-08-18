package org.robn.ecommerce.inventory.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record InventoryCreateRequest(

        @NotNull
        @Positive
        Long productId,

        @NotNull
        @Positive
        Long warehouseId,

        @NotNull
        @PositiveOrZero
        Integer stockQuantity

) {
}
