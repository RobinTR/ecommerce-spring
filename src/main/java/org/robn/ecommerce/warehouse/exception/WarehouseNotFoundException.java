package org.robn.ecommerce.warehouse.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a warehouse is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class WarehouseNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 5840701181300112820L;

    /**
     * Constructs a new {@link WarehouseNotFoundException} with the specified ID.
     *
     * @param id the ID of the warehouse that was not found.
     */
    private WarehouseNotFoundException(final Long id) {
        super(String.format("Warehouse with id %s not found.", id));
    }

    /**
     * Factory method to create a new instance of {@link WarehouseNotFoundException}.
     *
     * @param id the ID of the warehouse that was not found.
     * @return a new instance of {@link WarehouseNotFoundException}.
     */
    public static WarehouseNotFoundException of(final Long id) {
        return new WarehouseNotFoundException(id);
    }

}
