package org.robn.ecommerce.auth.exception;

import java.io.Serial;

/**
 * Base exception class for authentication-related errors in the EcoAuth system.
 * Extends RuntimeException to allow for unchecked exceptions.
 */
public class EcoAuthException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5570032363394153168L;

    /**
     * Constructs a new EcoAuthException with the specified detail message.
     *
     * @param message the detail message.
     */
    protected EcoAuthException(final String message) {
        super(message);
    }

    /**
     * Constructs a new EcoAuthException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    protected EcoAuthException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
