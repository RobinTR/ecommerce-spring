package org.robn.ecommerce.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import org.robn.ecommerce.common.util.validation.EmailAddress;
import org.robn.ecommerce.common.util.validation.Password;

public record EcoUserCreateRequest(

        @NotBlank
        @EmailAddress
        String email,

        @NotBlank
        @Password
        String password,

        @NotBlank
        String deviceId

) {
}
