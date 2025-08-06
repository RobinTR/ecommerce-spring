package org.robn.ecommerce.common.exception;

import java.io.Serial;

/**
 * Base class for exceptions that indicate a resource was not found.
 */
public abstract class EcoNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7564942602822378516L;

    /**
     * Constructs a new {@link EcoNotFoundException} with the specified detail message.
     *
     * @param message the detail message
     */
    protected EcoNotFoundException(final String message) {
        super(message);
    }

}
