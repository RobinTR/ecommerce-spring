package org.robn.ecommerce.category.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

/**
 * Exception thrown when a category does not exist.
 * This exception extends {@link EcoNotFoundException} to indicate that the requested category was not found.
 */
public final class CategoryNotExistException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1449080936555435056L;

    /**
     * Constructs a new {@link CategoryNotExistException} with the specified detail message.
     *
     * @param id the ID of the category that does not exist
     */
    private CategoryNotExistException(final Long id) {
        super("Category with id: " + id + " does not exist.");
    }

    /**
     * Factory method to create a new instance of {@link CategoryNotExistException}.
     *
     * @param id the ID of the category that does not exist
     * @return a new instance of {@link CategoryNotExistException}
     */
    public static CategoryNotExistException of(final Long id) {
        return new CategoryNotExistException(id);
    }

}
