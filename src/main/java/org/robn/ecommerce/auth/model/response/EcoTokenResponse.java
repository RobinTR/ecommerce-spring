package org.robn.ecommerce.auth.model.response;

public record EcoTokenResponse(

        String accessToken,
        String refreshToken

) {
}
