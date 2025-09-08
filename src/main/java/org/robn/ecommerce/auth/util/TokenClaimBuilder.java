package org.robn.ecommerce.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;
import org.robn.ecommerce.auth.model.EcoRole;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.enums.EcoTokenClaims;

@UtilityClass
public class TokenClaimBuilder {

    public static Claims buildClaims(final EcoUser user, final String deviceId) {
        final ClaimsBuilder claimsBuilder = Jwts.claims();
        claimsBuilder.add(EcoTokenClaims.SUBJECT.getValue(), user.getEmail());
        claimsBuilder.add(EcoTokenClaims.USER_ID.getValue(), user.getId().toString());
        claimsBuilder.add(EcoTokenClaims.USER_EMAIL.getValue(), user.getEmail());
        claimsBuilder.add(EcoTokenClaims.USER_ROLES.getValue(), user.getRoles().stream().map(EcoRole::getName).toList());
        claimsBuilder.add(EcoTokenClaims.DEVICE_ID.getValue(), deviceId);

        return claimsBuilder.build();
    }

}
