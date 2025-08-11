package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public class RelatedBrandNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -2355098044056883419L;

    /**
     * Constructs a new {@link RelatedBrandNotFoundException} with the specified detail message.
     *
     * @param id the ID of the related brand that does not exist
     */
    public RelatedBrandNotFoundException(final Long id) {
        super(String.format("Related brand with ID %d was not found.", id));
    }

}
