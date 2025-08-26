package org.robn.ecommerce.common.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class EcoErrorResponse extends EcoBaseResponse<String> {

    private String header;

    public static EcoErrorResponse failureOf(HttpStatus httpStatus, String message) {
        return EcoErrorResponse.builder()
                .httpStatus(httpStatus)
                .isSuccess(false)
                .response(message)
                .build();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Header {

        API_ERROR("API ERROR"),

        CONFLICT_ERROR("CONFLICT ERROR"),

        VALIDATION_ERROR("VALIDATION ERROR"),

        DATABASE_ERROR("DATABASE ERROR"),

        AUTH_ERROR("AUTH ERROR");

        private final String name;

    }

}
