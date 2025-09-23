package org.robn.ecommerce.inventory.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when an inventory record for a specific product and warehouse cannot be found.
 * This exception extends {@link EcoNotFoundException} to indicate that the requested resource does not exist.
 */
public class InventoryByProductAndWarehouseNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 8138796912014699872L;

    /**
     * Constructs a new {@link InventoryByProductAndWarehouseNotFoundException} with a detailed message.
     *
     * @param productId   the ID of the product for which the inventory was not found.
     * @param warehouseId the ID of the warehouse for which the inventory was not found.
     */
    private InventoryByProductAndWarehouseNotFoundException(final Long productId, final UUID warehouseId) {
        super(String.format(
                "Inventory not found for product with id %d and warehouse with id %s",
                productId, warehouseId
        ));
    }

    /**
     * Factory method to create an instance of {@link InventoryByProductAndWarehouseNotFoundException}.
     *
     * @param productId   the ID of the product.
     * @param warehouseId the ID of the warehouse.
     * @return a new instance of {@link InventoryByProductAndWarehouseNotFoundException}.
     */
    public static InventoryByProductAndWarehouseNotFoundException of(final Long productId, final UUID warehouseId) {
        return new InventoryByProductAndWarehouseNotFoundException(productId, warehouseId);
    }

}
