package org.robn.ecommerce.auth.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.robn.ecommerce.auth.model.enums.EcoTokenClaims;
import org.robn.ecommerce.common.model.BaseDomainModel;
import org.robn.ecommerce.auth.model.enums.EcoUserStatus;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EcoUser extends BaseDomainModel {

    private UUID id;
    private String email;
    private String password;
    private EcoUserStatus ecoUserStatus;
    private List<EcoRole> roles;

    public Claims getClaims() {
        final ClaimsBuilder claimsBuilder = Jwts.claims();

        claimsBuilder.add(EcoTokenClaims.SUBJECT.getValue(), this.email);
        claimsBuilder.add(EcoTokenClaims.USER_ID.getValue(), this.id.toString());
        claimsBuilder.add(EcoTokenClaims.USER_EMAIL.getValue(), this.email);
        claimsBuilder.add(EcoTokenClaims.USER_ROLES.getValue(), this.roles.stream().map(EcoRole::getName).toList());

        return claimsBuilder.build();
    }

}
