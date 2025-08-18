package org.robn.ecommerce.common.exception;

import java.io.Serial;

public class ImageUploadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4422493249947799335L;

    private ImageUploadException(String message) {
        super(message);
    }

    public static ImageUploadException of(String message) {
        return new ImageUploadException(message);
    }

}
