package org.robn.ecommerce.common.model.response;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
public class EcoErrorResponse extends EcoBaseResponse<String> {

    public static EcoErrorResponse failureOf(HttpStatus httpStatus, String message) {
        return EcoErrorResponse.builder()
                .httpStatus(httpStatus)
                .isSuccess(false)
                .response(message)
                .build();
    }

}
