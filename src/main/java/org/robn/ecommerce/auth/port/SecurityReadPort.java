package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.enums.Role;

import java.util.UUID;

public interface SecurityReadPort {

    UUID getCurrentUserId();

    boolean hasRole(Role role);

}
