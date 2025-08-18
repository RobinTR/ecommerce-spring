package org.robn.ecommerce.product.exception;

import java.io.Serial;

/**
 * Exception thrown when the number of images associated with a product exceeds the allowed limit.
 * This exception is used to indicate that a product cannot have more images than the defined maximum.
 */
public class ProductImageLimitExceededException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1543551133445330828L;

    /**
     * Constructs a new {@link ProductImageLimitExceededException} with the specified message.
     * this exception is thrown when the number of images associated with a product exceeds the allowed limit.
     * @param message the detail message.
     */
    private ProductImageLimitExceededException(final String message) {
        super(message);
    }

    /**
     * Factory method to create a new instance of {@link ProductImageLimitExceededException}.
     *
     * @param message the detail message.
     * @return a new instance of {@link ProductImageLimitExceededException}.
     */
    public static ProductImageLimitExceededException of(final String message) {
        return new ProductImageLimitExceededException(message);
    }

}
