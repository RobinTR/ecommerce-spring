package org.robn.ecommerce.auth.exception;

import java.io.Serial;

/**
 * Exception thrown when a token is not valid.
 * This exception extends {@link EcoAuthException} and provides static factory methods for creating instances.
 */
public class EcoInvalidTokenException extends EcoAuthException {

    @Serial
    private static final long serialVersionUID = 3610043563580453033L;

    /**
     * Private constructor to enforce the use of static factory methods.
     *
     * @param jwt   The JWT (JSON Web Token) that is not valid.
     */
    private EcoInvalidTokenException(String jwt) {
        super(String.format("Token is not valid! token: %s", jwt));
    }

    /**
     * Private constructor to enforce the use of static factory methods.
     *
     * @param jwt   The JWT (JSON Web Token) that is not valid.
     * @param cause The cause of the exception.
     */
    private EcoInvalidTokenException(String jwt, Throwable cause) {
        super(String.format("Token is not valid! token: %s", jwt), cause);
    }

    /**
     * Static factory method to create an instance of {@link EcoInvalidTokenException} with the specified JWT.
     *
     * @param jwt The JWT (JSON Web Token) that is not valid.
     * @return A new instance of {@link EcoInvalidTokenException}.
     */
    public static EcoInvalidTokenException of(final String jwt) {
        return new EcoInvalidTokenException(jwt);
    }

    /**
     * Static factory method to create an instance of {@link EcoInvalidTokenException} with the specified JWT and cause.
     *
     * @param jwt   The JWT (JSON Web Token) that is not valid.
     * @param cause The cause of the exception.
     * @return A new instance of {@link EcoInvalidTokenException}.
     */
    public static EcoInvalidTokenException of(final String jwt, final Throwable cause) {
        return new EcoInvalidTokenException(jwt, cause);
    }

}
