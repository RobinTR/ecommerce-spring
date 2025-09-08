package org.robn.ecommerce.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoInvalidRefreshTokenException;
import org.robn.ecommerce.auth.exception.EcoUserNotFoundException;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.RefreshToken;
import org.robn.ecommerce.auth.port.EcoUserReadPort;
import org.robn.ecommerce.auth.port.RefreshTokenReadPort;
import org.robn.ecommerce.auth.port.RefreshTokenSavePort;
import org.robn.ecommerce.auth.service.RefreshTokenService;
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
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenService refreshTokenServiceProxy;
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
            refreshTokenServiceProxy.revokeAllTokensForUserDevice(userId, existingToken.getDeviceId());
            throw EcoInvalidRefreshTokenException.of();
        }

        // Token reuse detection
        if (existingToken.isRevoked()) {
            refreshTokenServiceProxy.revokeAllTokensForUserDevice(userId, existingToken.getDeviceId());
            throw EcoInvalidRefreshTokenException.of();
        }

        // Token expired
        if (existingToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshTokenServiceProxy.revokeAllTokensForUserDevice(userId, existingToken.getDeviceId());
            throw EcoInvalidRefreshTokenException.of();
        }

        existingToken.setRevoked(true);
        refreshTokenSavePort.save(existingToken);

        return buildRefreshToken(userId, deviceId);
    }

    @Override
    @Transactional
    public void revokeAllTokensForUserDevice(final UUID userId, final String deviceId) {
        List<RefreshToken> tokens = refreshTokenReadPort.findAllByUserIdAndDeviceId(userId, deviceId);
        tokens.forEach(r -> {
            r.setRevoked(true);
            refreshTokenSavePort.save(r);
        });
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
