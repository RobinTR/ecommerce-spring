package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when an address is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class AddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -289432476352990484L;

    /**
     * Constructs a new {@link AddressNotFoundException} with the specified ID.
     *
     * @param id the ID of the address that was not found.
     */
    private AddressNotFoundException(final UUID id) {
        super(String.format("Address with id '%s' not found", id));
    }

    /**
     * Factory method to create a new instance of {@link AddressNotFoundException}.
     *
     * @param id the ID of the address that was not found.
     * @return a new instance of {@link AddressNotFoundException}.
     */
    public static AddressNotFoundException of(final UUID id) {
        return new AddressNotFoundException(id);
    }

}
