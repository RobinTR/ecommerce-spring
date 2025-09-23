package org.robn.ecommerce.inventory.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record InventoryCreateRequest(

        @NotNull
        @Positive
        Long productId,

        @NotNull
        UUID warehouseId,

        @NotNull
        @PositiveOrZero
        Integer quantity

) {
}
