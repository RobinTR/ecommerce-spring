package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a related product is not found.
 * This exception extends {@link EcoNotFoundException} to indicate that the related product
 * with the specified ID does not exist.
 */
public class RelatedProductNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -2215219886067769588L;

    /**
     * Constructs a new {@link RelatedProductNotFoundException} with the specified detail message.
     *
     * @param id the ID of the related product that does not exist
     */
    private RelatedProductNotFoundException(final Long id) {
        super(String.format("Related product with ID %d was not found.", id));
    }

    /**
     * Factory method to create a new instance of {@link RelatedProductNotFoundException}.
     *
     * @param id the ID of the related product that does not exist
     * @return a new instance of {@link RelatedProductNotFoundException}
     */
    public static RelatedProductNotFoundException of(final Long id) {
        return new RelatedProductNotFoundException(id);
    }

}
