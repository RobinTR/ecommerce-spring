package org.robn.ecommerce.warehouse.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public class WarehouseNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 5840701181300112820L;

    public WarehouseNotFoundException(final Long id) {
        super(String.format("Warehouse with id %s not found.", id));
    }
}
