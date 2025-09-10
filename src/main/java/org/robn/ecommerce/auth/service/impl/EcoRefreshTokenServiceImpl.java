package org.robn.ecommerce.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoInvalidRefreshTokenException;
import org.robn.ecommerce.auth.exception.EcoUserNotFoundException;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.RefreshToken;
import org.robn.ecommerce.auth.port.EcoUserReadPort;
import org.robn.ecommerce.auth.port.RefreshTokenReadPort;
import org.robn.ecommerce.auth.port.RefreshTokenSavePort;
import org.robn.ecommerce.auth.service.EcoRefreshTokenService;
import org.robn.ecommerce.common.model.enums.EcoConfigParameter;
import org.robn.ecommerce.parameter.port.EcoParameterReadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EcoRefreshTokenServiceImpl implements EcoRefreshTokenService {

    private final EcoRefreshTokenService ecoRefreshTokenServiceProxy;
    private final EcoUserReadPort ecoUserReadPort;
    private final RefreshTokenReadPort refreshTokenReadPort;
    private final RefreshTokenSavePort refreshTokenSavePort;
    private final EcoParameterReadPort ecoParameterReadPort;

    @Override
    @Transactional
    public RefreshToken generateRefreshToken(final UUID userId, final String deviceId) {
        final EcoUser user = ecoUserReadPort.findById(userId).orElseThrow(() -> EcoUserNotFoundException.of(userId));

        final RefreshToken refreshToken = buildRefreshToken(user.getId(), deviceId);

        return refreshTokenSavePort.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken rotate(final String oldToken, final UUID userId, final String deviceId) {
        final RefreshToken existingToken = refreshTokenReadPort.findByToken(oldToken).orElseThrow(EcoInvalidRefreshTokenException::of);

        if (!existingToken.getUserId().equals(userId)) {
            throw EcoInvalidRefreshTokenException.of();
        }

        if (!existingToken.getDeviceId().equals(deviceId)) {
            revokeTokensForDeviceAndThrow(userId, existingToken.getDeviceId());
        }

        // Token reuse detection
        if (existingToken.isRevoked()) {
            revokeTokensForDeviceAndThrow(userId, existingToken.getDeviceId());
        }

        // Token expired
        if (existingToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            revokeTokensForDeviceAndThrow(userId, existingToken.getDeviceId());
        }

        existingToken.setRevoked(true);
        refreshTokenSavePort.save(existingToken);

        final RefreshToken newToken = buildRefreshToken(userId, deviceId);

        return refreshTokenSavePort.save(newToken);
    }

    @Override
    @Transactional
    public void revokeAllTokensForUserDevice(final UUID userId, final String deviceId) {
        final List<RefreshToken> tokens = refreshTokenReadPort.findAllByUserIdAndDeviceId(userId, deviceId);
        final List<RefreshToken> revokedTokens = tokens.stream().map(token -> {
            token.setRevoked(true);
            return token;
        }).toList();
        refreshTokenSavePort.saveAll(revokedTokens);
    }

    private void revokeTokensForDeviceAndThrow(final UUID userId, final String deviceId) {
        ecoRefreshTokenServiceProxy.revokeAllTokensForUserDevice(userId, deviceId);
        throw EcoInvalidRefreshTokenException.of();
    }

    private LocalDateTime getRefreshTokenExpireAt() {
        final String expireDays = ecoParameterReadPort.findByName(EcoConfigParameter.AUTH_REFRESH_TOKEN_EXPIRE_DAY.name()).getDefinition();

        return LocalDateTime.now().plusDays(Integer.parseInt(expireDays));
    }

    private RefreshToken buildRefreshToken(final UUID userId, final String deviceId) {
        return RefreshToken.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .deviceId(deviceId)
                .revoked(false)
                .expiresAt(getRefreshTokenExpireAt())
                .build();
    }

}
