package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.EcoRefreshToken;

import java.util.UUID;

public interface EcoRefreshTokenService {

    EcoRefreshToken generateRefreshToken(UUID userId, String deviceId);

    EcoRefreshToken rotate(String oldToken, UUID userId, String deviceId);

    void revokeAllTokensForUserDevice(UUID userId, String deviceId);

}
