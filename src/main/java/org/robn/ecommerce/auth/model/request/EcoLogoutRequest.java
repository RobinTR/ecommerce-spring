package org.robn.ecommerce.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EcoLogoutRequest(

        @NotNull
        UUID userId,

        @NotBlank
        @Size(max = 255)
        String deviceId

) {
}
