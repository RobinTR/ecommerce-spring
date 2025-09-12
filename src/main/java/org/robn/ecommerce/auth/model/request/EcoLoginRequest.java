package org.robn.ecommerce.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.robn.ecommerce.common.util.validation.EmailAddress;
import org.robn.ecommerce.common.util.validation.Password;

public record EcoLoginRequest(

        @NotBlank
        @EmailAddress
        String email,

        @NotBlank
        @Password
        String password,

        @NotBlank
        @Size(max = 255)
        String deviceId

) {
}
