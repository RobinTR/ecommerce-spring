package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class CustomerAddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 2910324286247313804L;

    public CustomerAddressNotFoundException(final UUID id) {
        super(String.format("Customer Address with id '%s' not found", id));
    }

}
