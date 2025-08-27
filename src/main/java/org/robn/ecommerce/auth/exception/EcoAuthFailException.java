package org.robn.ecommerce.auth.exception;

import java.io.Serial;

public class EcoAuthFailException extends EcoAuthException {

    @Serial
    private static final long serialVersionUID = 795804895868213581L;

    private EcoAuthFailException(final String message) {
        super(message);
    }

    public static EcoAuthFailException of() {
        return new EcoAuthFailException("Authentication failed");
    }

}
