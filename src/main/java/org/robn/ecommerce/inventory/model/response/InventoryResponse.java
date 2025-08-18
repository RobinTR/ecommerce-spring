package org.robn.ecommerce.inventory.model.response;

public record InventoryResponse(

        Long id,
        Long productId,
        Long warehouseId,
        Integer stockQuantity

) {
}
