package org.robn.ecommerce.address.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a seller address is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class SellerAddressNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -1185037349292095420L;

    /**
     * Constructs a new {@link SellerAddressNotFoundException} with the specified address ID.
     *
     * @param addressId the ID of the seller address that was not found.
     */
    private SellerAddressNotFoundException(final UUID addressId) {
        super(String.format("Seller Address with id '%s' not found", addressId));
    }

    /**
     * Factory method to create a new instance of {@link SellerAddressNotFoundException}.
     *
     * @param addressId the ID of the seller address that was not found.
     * @return a new instance of {@link SellerAddressNotFoundException}.
     */
    public static SellerAddressNotFoundException of(final UUID addressId) {
        return new SellerAddressNotFoundException(addressId);
    }

}
