package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class SellerAddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -1185037349292095420L;

    public SellerAddressNotFoundException(final UUID id) {
        super(String.format("Seller Address with id '%s' not found", id));
    }

}
