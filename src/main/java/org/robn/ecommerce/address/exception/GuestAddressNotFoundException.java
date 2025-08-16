package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class GuestAddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -1850061629152994046L;

    public GuestAddressNotFoundException(final UUID id) {
        super(String.format("Guest Address with id '%s' not found", id));
    }

}
