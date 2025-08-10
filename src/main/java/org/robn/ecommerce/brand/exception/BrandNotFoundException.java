package org.robn.ecommerce.brand.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public final class BrandNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 2529033075050356615L;

    /**
     * Constructs a new {@link BrandNotFoundException} with the specified detail message.
     *
     * @param id the ID of the brand that does not exist
     */
    public BrandNotFoundException(final Long id) {
        super(String.format("Brand with id %d was not found.", id));
    }

}
