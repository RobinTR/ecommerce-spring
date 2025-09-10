package org.robn.ecommerce.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EcoLogoutRequest(

        @NotNull
        UUID userId,

        @NotBlank
        String deviceId

) {
}
