package org.robn.ecommerce.product.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when no images are found for a product.
 * This exception extends {@link EcoNotFoundException} to indicate that the requested resource (product images)
 * could not be found.
 */
public class ProductImageNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -6682732612171171753L;

    /**
     * Constructs a new {@link ProductImageNotFoundException} with the specified product ID.
     *
     * @param id the ID of the product for which no images were found.
     */
    private ProductImageNotFoundException(final Long id) {
        super(String.format("No images found for product with id: %d ", id));
    }

    /**
     * Factory method to create a new instance of {@link ProductImageNotFoundException}.
     *
     * @param id the ID of the product for which no images were found.
     * @return a new instance of {@link ProductImageNotFoundException}.
     */
    public static ProductImageNotFoundException of(final Long id) {
        return new ProductImageNotFoundException(id);
    }

}
