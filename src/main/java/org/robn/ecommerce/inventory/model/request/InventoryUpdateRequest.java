package org.robn.ecommerce.inventory.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record InventoryUpdateRequest(

        @NotNull
        @PositiveOrZero
        Integer stockQuantity

) {
}
