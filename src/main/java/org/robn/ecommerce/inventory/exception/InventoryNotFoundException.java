package org.robn.ecommerce.inventory.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public class InventoryNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 5306786571487007999L;

    public InventoryNotFoundException(final Long id) {
        super(String.format("Inventory with id %d not found", id));
    }

}
