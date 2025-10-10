package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a customer cart is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class CustomerCartNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 8200202379687948825L;

    /**
     * Constructs a new CustomerCartNotFoundException with the specified cart ID.
     *
     * @param cartId the ID of the customer cart that was not found
     */
    private CustomerCartNotFoundException(final UUID cartId) {
        super(String.format("Customer cart with ID %s not found", cartId));
    }

    /**
     * Factory method to create a new instance of CustomerCartNotFoundException.
     *
     * @param cartId the ID of the customer cart that was not found
     * @return a new instance of CustomerCartNotFoundException
     */
    public static CustomerCartNotFoundException of(final UUID cartId) {
        return new CustomerCartNotFoundException(cartId);
    }

}
