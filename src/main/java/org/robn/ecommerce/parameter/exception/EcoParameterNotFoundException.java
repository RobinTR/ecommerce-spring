package org.robn.ecommerce.parameter.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a parameter is not found.
 * This exception extends the {@link EcoNotFoundException} class.
 * It provides a factory method to create instances with a formatted message.
 */
public class EcoParameterNotFoundException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 2778946923660963534L;

    /**
     * Private constructor to enforce the use of the factory method.
     * Constructs a new {@link EcoParameterNotFoundException} with the specified detail message.
     *
     * @param message the detail message.
     */
    private EcoParameterNotFoundException(final String message) {
        super(message);
    }

    /**
     * Factory method to create a new {@link EcoParameterNotFoundException} instance.
     *
     * @param name the name of the parameter that was not found.
     * @return a new {@link EcoParameterNotFoundException} instance with a formatted message.
     */
    public static EcoParameterNotFoundException of(final String name) {
        return new EcoParameterNotFoundException(String.format("Parameter '%s' not found", name));
    }

}
