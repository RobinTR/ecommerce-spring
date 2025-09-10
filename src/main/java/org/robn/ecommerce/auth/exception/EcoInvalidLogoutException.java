package org.robn.ecommerce.auth.exception;

import java.io.Serial;

/**
 * Exception thrown when a logout request is invalid.
 */
public class EcoInvalidLogoutException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7425958169327130067L;

    // Default constructor with a predefined message
    // Made private to enforce the use of the static factory method
    private EcoInvalidLogoutException() {
        super("Invalid logout request.");
    }

    /**
     * Static factory method to create an instance of EcoInvalidLogoutException.
     *
     * @return a new instance of EcoInvalidLogoutException
     */
    public static EcoInvalidLogoutException of() {
        return new EcoInvalidLogoutException();
    }

}
