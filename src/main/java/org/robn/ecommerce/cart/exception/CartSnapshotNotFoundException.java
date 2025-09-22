package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a cart snapshot with the specified ID is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class CartSnapshotNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1362760622459310482L;

    /**
     * Constructs a new CartSnapshotNotFoundException with the specified cart snapshot ID.
     *
     * @param id the ID of the cart snapshot that was not found
     */
    private CartSnapshotNotFoundException(final UUID id) {
        super(String.format("Cart snapshot with id %s not found", id));
    }

    /**
     * Factory method to create a new instance of CartSnapshotNotFoundException.
     *
     * @param id the ID of the cart snapshot that was not found
     * @return a new instance of CartSnapshotNotFoundException
     */
    public static CartSnapshotNotFoundException of(final UUID id) {
        return new CartSnapshotNotFoundException(id);
    }

}
