package org.robn.ecommerce.common.model.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EcoErrorResponse extends EcoBaseResponse<String> {

    public EcoErrorResponse(HttpStatus httpStatus, String message) {
        super(LocalDateTime.now(), httpStatus, false, message);
    }

    public static EcoErrorResponse failureOf(HttpStatus httpStatus, String message) {
        return new EcoErrorResponse(httpStatus, message);
    }

}
