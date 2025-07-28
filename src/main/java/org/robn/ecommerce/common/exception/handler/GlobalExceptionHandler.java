package org.robn.ecommerce.common.exception.handler;

import org.robn.ecommerce.common.exception.EcoNotFoundException;
import org.robn.ecommerce.common.model.response.EcoErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EcoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<EcoErrorResponse> handleEcoNotFoundException(final EcoNotFoundException exception) {
        EcoErrorResponse ecoErrorResponse = EcoErrorResponse.failureOf(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(ecoErrorResponse, ecoErrorResponse.getHttpStatus());
    }
}
