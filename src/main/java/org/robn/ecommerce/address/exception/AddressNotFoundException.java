package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class AddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -289432476352990484L;

    public AddressNotFoundException(final UUID id) {
        super(String.format("Address with id '%s' not found", id));
    }

}
