package org.robn.ecommerce.cart.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a GuestCart is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class GuestCartNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -7247593651029522748L;

    /**
     * Constructs a new GuestCartNotFoundException with the specified cart ID.
     *
     * @param id the ID of the GuestCart that was not found
     */
    private GuestCartNotFoundException(final UUID id) {
        super(String.format("GuestCart with id %s not found", id));
    }

    /**
     * Factory method to create a new instance of GuestCartNotFoundException.
     *
     * @param id the ID of the GuestCart that was not found
     * @return a new instance of GuestCartNotFoundException
     */
    public static GuestCartNotFoundException of(final UUID id) {
        return new GuestCartNotFoundException(id);
    }

}
