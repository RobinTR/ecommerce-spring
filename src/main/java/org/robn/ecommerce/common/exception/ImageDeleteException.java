package org.robn.ecommerce.common.exception;

import java.io.Serial;

public class ImageDeleteException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5676093906932535998L;

    private ImageDeleteException(String message) {
        super(message);
    }

    public static ImageDeleteException of(String message) {
        return new ImageDeleteException(message);
    }

}
