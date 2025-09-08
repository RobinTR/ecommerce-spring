package org.robn.ecommerce.auth.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;
import java.util.UUID;

/**
 * Exception thrown when a user is not found in the system.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class EcoUserNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = -5020770465060116783L;

    /**
     * Constructs a new {@link EcoUserNotFoundException} with the specified user ID.
     *
     * @param userId the ID of the user that was not found.
     */
    private EcoUserNotFoundException(final UUID userId) {
        super(String.format("User with id %s not found", userId));
    }

    /**
     * Factory method to create a new instance of {@link EcoUserNotFoundException}.
     *
     * @param userId the ID of the user that was not found.
     * @return a new instance of {@link EcoUserNotFoundException}.
     */
    public static EcoUserNotFoundException of(final UUID userId) {
        return new EcoUserNotFoundException(userId);
    }

}
