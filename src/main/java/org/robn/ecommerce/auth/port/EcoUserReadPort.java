package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.EcoUser;

import java.util.Optional;
import java.util.UUID;

public interface EcoUserReadPort {

    Optional<EcoUser> findById(UUID id);

    Optional<EcoUser> findByEmail(String email);

}
