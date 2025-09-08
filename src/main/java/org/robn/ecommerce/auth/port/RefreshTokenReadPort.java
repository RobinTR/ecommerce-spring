package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.RefreshToken;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenReadPort {

    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findAllByUserIdAndDeviceId(UUID userId, String deviceId);

}
