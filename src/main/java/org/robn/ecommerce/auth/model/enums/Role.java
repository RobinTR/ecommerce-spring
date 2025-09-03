package org.robn.ecommerce.auth.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    CUSTOMER("ROLE_CUSTOMER"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SELLER("ROLE_SELLER");

    private final String authority;

}
