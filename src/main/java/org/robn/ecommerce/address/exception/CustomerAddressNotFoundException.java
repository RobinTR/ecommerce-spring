package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a customer address is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a not found error.
 */
public class CustomerAddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 2910324286247313804L;

    /**
     * Constructs a new {@link CustomerAddressNotFoundException} with the specified address ID.
     *
     * @param addressId the ID of the customer address that was not found.
     */
    private CustomerAddressNotFoundException(final UUID addressId) {
        super(String.format("Customer Address with id '%s' not found", addressId));
    }

    /**
     * Factory method to create a new instance of {@link CustomerAddressNotFoundException}.
     *
     * @param addressId the ID of the customer address that was not found.
     * @return a new instance of {@link CustomerAddressNotFoundException}.
     */
    public static CustomerAddressNotFoundException of(final UUID addressId) {
        return new CustomerAddressNotFoundException(addressId);
    }

}
