package org.robn.ecommerce.auth.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ROLE_ADMIN", List.of("ROLE_USER", "ROLE_ADMIN")),
    CUSTOMER("ROLE_CUSTOMER", List.of("ROLE_USER", "ROLE_CUSTOMER")),
    SELLER("ROLE_SELLER", List.of("ROLE_USER", "ROLE_SELLER")),
    USER("ROLE_USER", List.of("ROLE_USER"));

    private final String authority;
    private final List<String> assignedRoles;

}
