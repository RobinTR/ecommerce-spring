package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a cart is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class CartNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -3562260604663613724L;

    /**
     * Constructs a new CartNotFoundException with the specified cart ID.
     *
     * @param id the ID of the cart that was not found
     */
    private CartNotFoundException(final UUID id) {
        super(String.format("Cart with id %s not found", id));
    }

    /**
     * Factory method to create a new instance of CartNotFoundException.
     *
     * @param id the ID of the cart that was not found
     * @return a new instance of CartNotFoundException
     */
    public static CartNotFoundException of(final UUID id) {
        return new CartNotFoundException(id);
    }

}
