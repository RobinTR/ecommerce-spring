package org.robn.ecommerce.auth.model.response;

import java.util.UUID;

public record EcoUserResponse(

        UUID id,
        String email

) {
}
