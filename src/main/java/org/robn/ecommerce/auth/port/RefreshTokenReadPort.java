package org.robn.ecommerce.auth.port;

import org.robn.ecommerce.auth.model.EcoRefreshToken;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenReadPort {

    Optional<EcoRefreshToken> findByToken(String token);

    List<EcoRefreshToken> findAllByUserIdAndDeviceId(UUID userId, String deviceId);

}
