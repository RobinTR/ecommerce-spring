package org.robn.ecommerce.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.exception.EcoInvalidRefreshTokenException;
import org.robn.ecommerce.auth.exception.EcoUserNotFoundException;
import org.robn.ecommerce.auth.model.EcoRefreshToken;
import org.robn.ecommerce.auth.model.EcoUser;
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

    private final EcoUserReadPort ecoUserReadPort;
    private final RefreshTokenReadPort refreshTokenReadPort;
    private final RefreshTokenSavePort refreshTokenSavePort;
    private final EcoParameterReadPort ecoParameterReadPort;

    @Override
    @Transactional
    public EcoRefreshToken generateRefreshToken(final UUID userId, final String deviceId) {
        final EcoUser user = ecoUserReadPort.findById(userId).orElseThrow(() -> EcoUserNotFoundException.of(userId));

        final EcoRefreshToken ecoRefreshToken = buildRefreshToken(user.getId(), deviceId);

        return refreshTokenSavePort.save(ecoRefreshToken);
    }

    @Override
    @Transactional
    public EcoRefreshToken rotate(final String oldToken, final UUID userId, final String deviceId) {
        final EcoRefreshToken existingToken = refreshTokenReadPort.findByToken(oldToken).orElseThrow(EcoInvalidRefreshTokenException::of);

        if (!existingToken.getUserId().equals(userId)) {
            throw EcoInvalidRefreshTokenException.of();
        }

        if (!existingToken.getDeviceId().equals(deviceId)) {
            this.revokeAllTokensForUserDevice(userId, existingToken.getDeviceId());
            throw EcoInvalidRefreshTokenException.of();
        }

        // Token reuse detection
        if (existingToken.isRevoked()) {
            this.revokeAllTokensForUserDevice(userId, existingToken.getDeviceId());
            throw EcoInvalidRefreshTokenException.of();
        }

        // Token expired
        if (existingToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            this.revokeAllTokensForUserDevice(userId, existingToken.getDeviceId());
            throw EcoInvalidRefreshTokenException.of();
        }

        existingToken.setRevoked(true);
        refreshTokenSavePort.save(existingToken);

        final EcoRefreshToken newToken = buildRefreshToken(userId, deviceId);

        return refreshTokenSavePort.save(newToken);
    }

    @Override
    @Transactional
    public void revokeAllTokensForUserDevice(final UUID userId, final String deviceId) {
        final List<EcoRefreshToken> tokens = refreshTokenReadPort.findAllByUserIdAndDeviceId(userId, deviceId);
        final List<EcoRefreshToken> revokedTokens = tokens.stream().map(token -> {
            token.setRevoked(true);
            return token;
        }).toList();
        refreshTokenSavePort.saveAll(revokedTokens);
    }

    private LocalDateTime getRefreshTokenExpireAt() {
        final String expireDays = ecoParameterReadPort.findByName(EcoConfigParameter.AUTH_REFRESH_TOKEN_EXPIRE_DAY.name()).getDefinition();

        return LocalDateTime.now().plusDays(Integer.parseInt(expireDays));
    }

    private EcoRefreshToken buildRefreshToken(final UUID userId, final String deviceId) {
        return EcoRefreshToken.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .deviceId(deviceId)
                .revoked(false)
                .expiresAt(getRefreshTokenExpireAt())
                .build();
    }

}
