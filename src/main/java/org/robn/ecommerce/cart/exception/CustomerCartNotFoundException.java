package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class CustomerCartNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 6797820916001080423L;

    private CustomerCartNotFoundException(final UUID cartId) {
        super(String.format("Customer cart with ID %s not found", cartId));
    }

    public static CustomerCartNotFoundException of(final UUID cartId) {
        return new CustomerCartNotFoundException(cartId);
    }

}
