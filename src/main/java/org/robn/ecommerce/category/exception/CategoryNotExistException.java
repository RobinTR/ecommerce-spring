package org.robn.ecommerce.category.exception;

import org.robn.ecommerce.common.exception.EcoNotFoundException;

import java.io.Serial;

public final class CategoryNotExistException extends EcoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1449080936555435056L;

    /**
     * Constructs a new {@link CategoryNotExistException} with the specified detail message.
     *
     * @param id the ID of the category that does not exist
     */
    public CategoryNotExistException(final Long id) {
        super("Category with id: " + id + " does not exist.");
    }

}
