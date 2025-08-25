package org.robn.ecommerce.auth.exception;

import org.robn.ecommerce.common.exception.EcoConflictException;

import java.io.Serial;

/**
 * Exception thrown when a user with the specified email already exists.
 * This exception extends {@link EcoConflictException} to indicate a conflict in user creation due to duplicate email.
 */
public class EcoUserAlreadyExistsByEmailException extends EcoConflictException {

    @Serial
    private static final long serialVersionUID = 8189117037568561431L;

    /**
     * Private constructor to create an instance of EcoUserAlreadyExistsByEmailException with a formatted message.
     *
     * @param email The email that already exists.
     */
    private EcoUserAlreadyExistsByEmailException(final String email) {
        super(String.format("User with email %s already exists", email));
    }

    /**
     * Static factory method to create an instance of EcoUserAlreadyExistsByEmailException.
     *
     * @param email The email that already exists.
     * @return A new instance of EcoUserAlreadyExistsByEmailException.
     */
    public static EcoUserAlreadyExistsByEmailException of(final String email) {
        return new EcoUserAlreadyExistsByEmailException(email);
    }

}
