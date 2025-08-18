package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a product with a specific ID does not exist.
 * This exception extends {@link EcoNotFoundException} to indicate that the requested product was not found.
 */
public class ProductNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 911135014070212817L;

    /**
     * Constructs a new {@link ProductNotFoundException} with the specified detail message.
     *
     * @param id the ID of the product that does not exist
     */
    private ProductNotFoundException(final Long id) {
        super(String.format("Product with ID %d was not found.", id));
    }

    /**
     * Factory method to create a new instance of {@link ProductNotFoundException}.
     *
     * @param id the ID of the product that does not exist
     * @return a new instance of {@link ProductNotFoundException}
     */
    public static ProductNotFoundException of(final Long id) {
        return new ProductNotFoundException(id);
    }

}
