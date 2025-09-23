package org.robn.ecommerce.inventory.model.response;

import java.util.UUID;

public record InventoryResponse(

        Long id,
        Long productId,
        UUID warehouseId,
        Integer stockQuantity

) {
}
