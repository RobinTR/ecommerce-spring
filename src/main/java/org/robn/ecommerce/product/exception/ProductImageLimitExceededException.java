package org.robn.ecommerce.product.exception;

import java.io.Serial;

public class ProductImageLimitExceededException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1543551133445330828L;

    public ProductImageLimitExceededException(final String message) {
        super(message);
    }
}
