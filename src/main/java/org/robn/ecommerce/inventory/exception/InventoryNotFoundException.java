package org.robn.ecommerce.inventory.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when an inventory item is not found in the system.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class InventoryNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 5306786571487007999L;

    /**
     * Constructs a new {@link InventoryNotFoundException} with the specified ID.
     *
     * @param id the ID of the inventory that was not found.
     */
    private InventoryNotFoundException(final Long id) {
        super(String.format("Inventory with id %d not found", id));
    }

    /**
     * Factory method to create a new instance of {@link InventoryNotFoundException}.
     *
     * @param id the ID of the inventory that was not found.
     * @return a new instance of {@link InventoryNotFoundException}.
     */
    public static InventoryNotFoundException of(final Long id) {
        return new InventoryNotFoundException(id);
    }

}
