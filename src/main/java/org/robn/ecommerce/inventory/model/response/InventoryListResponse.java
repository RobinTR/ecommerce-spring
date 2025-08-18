package org.robn.ecommerce.inventory.model.response;

public record InventoryListResponse(

        Long id,
        Long productId,
        Long warehouseId,
        Integer stockQuantity

) {
}
