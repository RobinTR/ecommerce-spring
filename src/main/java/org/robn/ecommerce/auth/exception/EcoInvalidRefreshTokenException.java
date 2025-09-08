package org.robn.ecommerce.auth.exception;

import java.io.Serial;

/**
 * Exception thrown when a refresh token is not valid.
 * This exception extends {@link RuntimeException} and provides a static factory method for creating instances.
 */
public class EcoInvalidRefreshTokenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6039095142842065065L;

    /**
     * Private constructor to enforce the use of the static factory method.
     * Sets a default error message.
     */
    private EcoInvalidRefreshTokenException() {
        super("Refresh token is not valid!");
    }

    /**
     * Static factory method to create an instance of {@link EcoInvalidRefreshTokenException}.
     *
     * @return A new instance of {@link EcoInvalidRefreshTokenException}.
     */
    public static EcoInvalidRefreshTokenException of() {
        return new EcoInvalidRefreshTokenException();
    }

}
