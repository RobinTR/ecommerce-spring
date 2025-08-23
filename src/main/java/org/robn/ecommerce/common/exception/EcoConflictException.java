package org.robn.ecommerce.common.exception;

import java.io.Serial;

public class EcoConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8120726459169791006L;

    protected EcoConflictException(final String message) {
        super(message);
    }

}
