package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a guest address is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class GuestAddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -1850061629152994046L;

    /**
     * Constructs a new {@link GuestAddressNotFoundException} with the specified address ID.
     *
     * @param addressId the ID of the guest address that was not found.
     */
    private GuestAddressNotFoundException(final UUID addressId) {
        super(String.format("Guest Address with id '%s' not found", addressId));
    }

    /**
     * Factory method to create a new instance of {@link GuestAddressNotFoundException}.
     *
     * @param addressId the ID of the guest address that was not found.
     * @return a new instance of {@link GuestAddressNotFoundException}.
     */
    public static GuestAddressNotFoundException of(final UUID addressId) {
        return new GuestAddressNotFoundException(addressId);
    }

}
