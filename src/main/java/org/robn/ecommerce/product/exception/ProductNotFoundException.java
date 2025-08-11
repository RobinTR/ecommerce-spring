package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public class ProductNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 911135014070212817L;

    /**
     * Constructs a new {@link ProductNotFoundException} with the specified detail message.
     *
     * @param id the ID of the product that does not exist
     */
    public ProductNotFoundException(final Long id) {
        super(String.format("Product with ID %d was not found.", id));
    }

}
