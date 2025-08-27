package org.robn.ecommerce.auth.exception;

import java.io.Serial;

public class EcoAccessDeniedException extends EcoAuthException {

    @Serial
    private static final long serialVersionUID = -7553085781654841004L;

    private EcoAccessDeniedException(final String message) {
        super(message);
    }

    public static EcoAccessDeniedException of() {
        return new EcoAccessDeniedException("You do not have permission to access this resource");
    }

}
