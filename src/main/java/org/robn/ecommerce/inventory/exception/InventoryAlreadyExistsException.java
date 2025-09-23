package org.robn.ecommerce.inventory.exception;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when an inventory already exists for a given product and warehouse.
 * This exception is used to indicate that an attempt was made to create an inventory record
 * for a product in a warehouse where one already exists.
 */
public class InventoryAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6651126916958365265L;

    /**
     * Constructs a new {@link InventoryAlreadyExistsException} with the specified productId and warehouseId.
     *
     * @param productId   the ID of the product.
     * @param warehouseId the ID of the warehouse.
     */
    private InventoryAlreadyExistsException(final Long productId, final UUID warehouseId) {
        super(String.format("Inventory already exists for productId: %d and warehouseId: %s", productId, warehouseId));
    }

    /**
     * Factory method to create a new instance of {@link InventoryAlreadyExistsException}.
     *
     * @param productId   the ID of the product.
     * @param warehouseId the ID of the warehouse.
     * @return a new instance of {@link InventoryAlreadyExistsException}.
     */
    public static InventoryAlreadyExistsException of(final Long productId, final UUID warehouseId) {
        return new InventoryAlreadyExistsException(productId, warehouseId);
    }

}
