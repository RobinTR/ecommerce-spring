package org.robn.ecommerce.brand.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public final class BrandNotExistException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 2529033075050356615L;

    /**
     * Constructs a new {@link BrandNotExistException} with the specified detail message.
     *
     * @param id the ID of the brand that does not exist
     */
    public BrandNotExistException(final Long id) {
        super("Brand with id: " + id + " does not exist.");
    }

}
