package org.robn.ecommerce.common.exception;

import java.io.Serial;

public class EcoNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7564942602822378516L;

    public EcoNotFoundException(String message) {
        super(message);
    }

}
