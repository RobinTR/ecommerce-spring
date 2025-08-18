package org.robn.ecommerce.brand.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a brand with a specific ID does not exist.
 * This exception extends {@link EcoNotFoundException} to indicate a not found error.
 */
public final class BrandNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 2529033075050356615L;

    /**
     * Constructs a new {@link BrandNotFoundException} with the specified detail message.
     *
     * @param id the ID of the brand that does not exist
     */
    private BrandNotFoundException(final Long id) {
        super(String.format("Brand with id %d was not found.", id));
    }

    /**
     * Factory method to create a new instance of {@link BrandNotFoundException}.
     *
     * @param id the ID of the brand that does not exist
     * @return a new instance of {@link BrandNotFoundException}
     */
    public static BrandNotFoundException of(final Long id) {
        return new BrandNotFoundException(id);
    }

}
