package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenReadPort {

    Optional<RefreshToken> findByTokenHash(String tokenHash);

}
