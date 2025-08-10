package org.robn.ecommerce.common.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.robn.ecommerce.common.exception.EcoNotFoundException;
import org.robn.ecommerce.common.model.response.EcoErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EcoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<EcoErrorResponse> handleEcoNotFoundException(final EcoNotFoundException exception) {
        EcoErrorResponse ecoErrorResponse = EcoErrorResponse.failureOf(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(ecoErrorResponse, ecoErrorResponse.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<EcoErrorResponse> handleValidationException(final MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        EcoErrorResponse ecoErrorResponse = EcoErrorResponse.failureOf(HttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(ecoErrorResponse, ecoErrorResponse.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<EcoErrorResponse> handleConstraintViolationException(final ConstraintViolationException exception) {
        String errorMessage = exception.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        EcoErrorResponse ecoErrorResponse = EcoErrorResponse.failureOf(HttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(ecoErrorResponse, ecoErrorResponse.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<EcoErrorResponse> handleDataIntegrityViolationException(final DataIntegrityViolationException exception) {
        EcoErrorResponse ecoErrorResponse = EcoErrorResponse.failureOf(
                HttpStatus.BAD_REQUEST,
                "Invalid request: referential integrity check failed. Please verify related fields."
        );

        return new ResponseEntity<>(ecoErrorResponse, ecoErrorResponse.getHttpStatus());
    }

}
