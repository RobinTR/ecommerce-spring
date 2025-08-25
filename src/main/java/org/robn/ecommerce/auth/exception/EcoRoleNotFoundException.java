package org.robn.ecommerce.auth.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a role is not found.
 * This exception extends {@link EcoNotFoundException} to indicate a specific type of not found error.
 */
public class EcoRoleNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 3273487531926055552L;

    /**
     * Private constructor to enforce the use of the static factory method.
     *
     * @param message The detail message for the exception.
     */
    private EcoRoleNotFoundException(final String message) {
        super(message);
    }

    /**
     * Static factory method to create an instance of {@link EcoRoleNotFoundException}
     * with a message indicating the role name that was not found.
     *
     * @param roleName The name of the role that was not found.
     * @return A new instance of {@link EcoRoleNotFoundException}.
     */
    public static EcoRoleNotFoundException of(final String roleName) {
        return new EcoRoleNotFoundException("Role not found with name: " + roleName);
    }

}
