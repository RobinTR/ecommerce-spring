package org.robn.ecommerce.common.exception;

import java.io.Serial;

public class ImageUploadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4422493249947799335L;

    public ImageUploadException(String message) {
        super(message);
    }

}
