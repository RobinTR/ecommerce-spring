package org.robn.ecommerce.inventory.exception;

import java.io.Serial;

public class InventoryAlreadyExistsException extends RuntimeException {

    @Serial
    private  static final long serialVersionUID = -7478866117758568108L;

    private InventoryAlreadyExistsException(final Long productId, final Long warehouseId) {
        super(String.format("Inventory already exists for productId: %d and warehouseId: %d", productId, warehouseId));
    }

    public static InventoryAlreadyExistsException of(final Long productId, final Long warehouseId) {
        return new InventoryAlreadyExistsException(productId, warehouseId);
    }

}
