package org.robn.ecommerce.auth.service;

import org.robn.ecommerce.auth.model.RefreshToken;

import java.util.UUID;

public interface EcoRefreshTokenService {

    RefreshToken generateRefreshToken(UUID userId, String deviceId);

    RefreshToken rotate(String oldToken, UUID userId, String deviceId);

    void revokeAllTokensForUserDevice(UUID userId, String deviceId);

}
