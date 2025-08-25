package org.robn.ecommerce.auth.exception;

import java.io.Serial;

/**
 * Exception thrown when an email or password is invalid during authentication.
 * This exception extends {@link EcoAuthException} and provides a specific message
 * indicating that the email or password provided is incorrect.
 */
public class EcoInvalidEmailOrPasswordException extends EcoAuthException {

    @Serial
    private static final long serialVersionUID = -2770397886649664732L;

    /**
     * Private constructor to enforce the use of the static factory method.
     * Initializes the exception with a predefined message.
     */
    private EcoInvalidEmailOrPasswordException() {
        super("Invalid email or password!");
    }

    /**
     * Static factory method to create a new instance of {@code EcoInvalidEmailOrPasswordException}.
     *
     * @return A new instance of {@code EcoInvalidEmailOrPasswordException}.
     */
    public static EcoInvalidEmailOrPasswordException of() {
        return new EcoInvalidEmailOrPasswordException();
    }

}
