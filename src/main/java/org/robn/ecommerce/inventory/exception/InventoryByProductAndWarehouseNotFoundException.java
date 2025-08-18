package org.robn.ecommerce.inventory.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public class InventoryByProductAndWarehouseNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -7462544872418353841L;

    private InventoryByProductAndWarehouseNotFoundException(final Long productId, final Long warehouseId) {
        super(String.format(
                "Inventory not found for product with id %d and warehouse with id %d",
                productId, warehouseId
        ));
    }

    public static InventoryByProductAndWarehouseNotFoundException of(final Long productId, final Long warehouseId) {
        return new InventoryByProductAndWarehouseNotFoundException(productId, warehouseId);
    }

}
