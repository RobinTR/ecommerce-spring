package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.EcoUser;

import java.util.Optional;

public interface EcoUserReadPort {

    Optional<EcoUser> findByEmail(String email);

    boolean emailExists(String email);

}
