package org.robn.ecommerce.inventory.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;
import org.robn.ecommerce.inventory.model.enums.StockType;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when an inventory record is not found for a given product ID, warehouse ID, and stock type.
 * This exception extends {@link EcoNotFoundException} to indicate that the requested resource does not exist.
 */
public class InventoryByProductAndWarehouseAndStockTypeNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -168433793102025568L;

    /**
     * Constructs a new {@link InventoryByProductAndWarehouseAndStockTypeNotFoundException} with a detailed message.
     *
     * @param productId   the ID of the product for which the inventory was not found.
     * @param warehouseId the ID of the warehouse for which the inventory was not found.
     * @param stockType   the type of stock for which the inventory was not found.
     */
    private InventoryByProductAndWarehouseAndStockTypeNotFoundException(final Long productId, final UUID warehouseId, final StockType stockType) {
        super(String.format(
                "Inventory not found for product ID %d in warehouse ID %s with stock type %s",
                productId, warehouseId, stockType.name()
        ));
    }

    /**
     * Factory method to create an instance of {@link InventoryByProductAndWarehouseAndStockTypeNotFoundException}.
     *
     * @param productId   the ID of the product.
     * @param warehouseId the ID of the warehouse.
     * @param stockType   the type of stock.
     * @return a new instance of {@link InventoryByProductAndWarehouseAndStockTypeNotFoundException}.
     */
    public static InventoryByProductAndWarehouseAndStockTypeNotFoundException of(final Long productId, final UUID warehouseId, final StockType stockType) {
        return new InventoryByProductAndWarehouseAndStockTypeNotFoundException(productId, warehouseId, stockType);
    }

}
