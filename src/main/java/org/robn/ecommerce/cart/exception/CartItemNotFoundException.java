package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a cart item is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class CartItemNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -26816156285383729L;

    /**
     * Constructs a new CartItemNotFoundException with the specified cart item ID.
     *
     * @param cartId the ID of the cart item that was not found
     */
    private CartItemNotFoundException(final UUID cartId) {
        super(String.format("Cart Item with id %s not found", cartId));
    }

    /**
     * Factory method to create a new instance of CartItemNotFoundException.
     *
     * @param cartId the ID of the cart item that was not found
     * @return a new instance of CartItemNotFoundException
     */
    public static CartItemNotFoundException of(final UUID cartId) {
        return new CartItemNotFoundException(cartId);
    }

}
