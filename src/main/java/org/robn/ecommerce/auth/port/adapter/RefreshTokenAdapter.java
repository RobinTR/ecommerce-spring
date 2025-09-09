package org.robn.ecommerce.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.RefreshTokenDomainToEntityMapper;
import org.robn.ecommerce.auth.mapper.RefreshTokenEntityToDomainMapper;
import org.robn.ecommerce.auth.model.RefreshToken;
import org.robn.ecommerce.auth.model.entity.RefreshTokenEntity;
import org.robn.ecommerce.auth.port.RefreshTokenReadPort;
import org.robn.ecommerce.auth.port.RefreshTokenSavePort;
import org.robn.ecommerce.auth.repository.RefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenReadPort, RefreshTokenSavePort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenEntityToDomainMapper entityToDomainMapper;
    private final RefreshTokenDomainToEntityMapper domainToEntityMapper;

    @Override
    public Optional<RefreshToken> findByToken(final String token) {
        final Optional<RefreshTokenEntity> entity = refreshTokenRepository.findByTokenHash(token);

        return entity.map(entityToDomainMapper::map);
    }

    @Override
    public List<RefreshToken> findAllByUserIdAndDeviceId(final UUID userId, final String deviceId) {
        final List<RefreshTokenEntity> entities = refreshTokenRepository.findAllByUserIdAndDeviceId(userId, deviceId);

        return entityToDomainMapper.map(entities);
    }

    @Override
    public RefreshToken save(final RefreshToken refreshToken) {
        final RefreshTokenEntity entity = domainToEntityMapper.map(refreshToken);
        final RefreshTokenEntity savedEntity = refreshTokenRepository.save(entity);

        return entityToDomainMapper.map(savedEntity);
    }

    @Override
    public List<RefreshToken> saveAll(final List<RefreshToken> tokens) {
        final List<RefreshTokenEntity> entities = domainToEntityMapper.map(tokens);
        final List<RefreshTokenEntity> savedEntities = refreshTokenRepository.saveAll(entities);

        return entityToDomainMapper.map(savedEntities);
    }

}
