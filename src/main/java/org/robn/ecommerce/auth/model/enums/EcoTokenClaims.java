package org.robn.ecommerce.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EcoTokenClaims {

    ISSUER("iss"),
    SUBJECT("sub"),
    USER_ID("userId"),
    USER_EMAIL("userEmail"),
    USER_ROLES("roles"),
    ISSUED_AT("iat"),
    EXPIRES_AT("exp"),
    ALGORITHM("alg");

    private final String value;

}
