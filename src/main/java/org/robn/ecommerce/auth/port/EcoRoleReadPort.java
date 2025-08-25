package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.EcoRole;

import java.util.Optional;

public interface EcoRoleReadPort {

    Optional<EcoRole> findByName(String name);

}
