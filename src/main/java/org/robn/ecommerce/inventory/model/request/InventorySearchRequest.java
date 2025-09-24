package org.robn.ecommerce.inventory.model.request;

import jakarta.validation.constraints.NotNull;
import org.robn.ecommerce.inventory.model.enums.StockType;

import java.util.UUID;

public record InventorySearchRequest(

        @NotNull
        Long productId,

        @NotNull
        UUID warehouseId,

        @NotNull
        StockType stockType

) {
}
