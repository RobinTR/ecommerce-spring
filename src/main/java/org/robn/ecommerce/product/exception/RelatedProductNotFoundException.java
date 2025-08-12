package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public class RelatedProductNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -2215219886067769588L;

    /**
     * Constructs a new {@link RelatedProductNotFoundException} with the specified detail message.
     *
     * @param id the ID of the related product that does not exist
     */
    public RelatedProductNotFoundException(Long id) {
        super(String.format("Related product with ID %d was not found.", id));
    }

}
