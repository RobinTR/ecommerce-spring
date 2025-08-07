package org.robn.ecommerce.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class EcoBaseResponse<T> {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private HttpStatus httpStatus;

    private Boolean isSuccess;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    public static <T> EcoBaseResponse<T> success() {
        return EcoBaseResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .build();
    }

    public static <T> EcoBaseResponse<T> successOf(final T response) {
        return EcoBaseResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .response(response)
                .build();
    }

}
