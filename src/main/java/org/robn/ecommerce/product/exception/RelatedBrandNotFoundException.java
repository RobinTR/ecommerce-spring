package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a related brand is not found.
 * This exception extends {@link EcoNotFoundException} to indicate that the related brand
 * with the specified ID does not exist.
 */
public class RelatedBrandNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -2355098044056883419L;

    /**
     * Constructs a new {@link RelatedBrandNotFoundException} with the specified detail message.
     *
     * @param id the ID of the related brand that does not exist
     */
    private RelatedBrandNotFoundException(final Long id) {
        super(String.format("Related brand with ID %d was not found.", id));
    }

    /**
     * Factory method to create a new instance of {@link RelatedBrandNotFoundException}.
     *
     * @param id the ID of the related brand that does not exist
     * @return a new instance of {@link RelatedBrandNotFoundException}
     */
    public static RelatedBrandNotFoundException of(final Long id) {
        return new RelatedBrandNotFoundException(id);
    }

}
