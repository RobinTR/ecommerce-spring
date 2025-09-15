package org.robn.ecommerce.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.RefreshTokenDomainToEntityMapper;
import org.robn.ecommerce.auth.mapper.RefreshTokenEntityToDomainMapper;
import org.robn.ecommerce.auth.model.EcoRefreshToken;
import org.robn.ecommerce.auth.model.entity.RefreshTokenEntity;
import org.robn.ecommerce.auth.port.RefreshTokenReadPort;
import org.robn.ecommerce.auth.port.RefreshTokenSavePort;
import org.robn.ecommerce.auth.repository.EcoRefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenReadPort, RefreshTokenSavePort {

    private final EcoRefreshTokenRepository ecoRefreshTokenRepository;
    private final RefreshTokenEntityToDomainMapper entityToDomainMapper;
    private final RefreshTokenDomainToEntityMapper domainToEntityMapper;

    @Override
    public Optional<EcoRefreshToken> findByToken(final String token) {
        final Optional<RefreshTokenEntity> entity = ecoRefreshTokenRepository.findByToken(token);

        return entity.map(entityToDomainMapper::map);
    }

    @Override
    public List<EcoRefreshToken> findAllByUserIdAndDeviceId(final UUID userId, final String deviceId) {
        final List<RefreshTokenEntity> entities = ecoRefreshTokenRepository.findAllByUserIdAndDeviceId(userId, deviceId);

        return entityToDomainMapper.map(entities);
    }

    @Override
    public EcoRefreshToken save(final EcoRefreshToken ecoRefreshToken) {
        final RefreshTokenEntity entity = domainToEntityMapper.map(ecoRefreshToken);
        final RefreshTokenEntity savedEntity = ecoRefreshTokenRepository.save(entity);

        return entityToDomainMapper.map(savedEntity);
    }

    @Override
    public List<EcoRefreshToken> saveAll(final List<EcoRefreshToken> tokens) {
        final List<RefreshTokenEntity> entities = domainToEntityMapper.map(tokens);
        final List<RefreshTokenEntity> savedEntities = ecoRefreshTokenRepository.saveAll(entities);

        return entityToDomainMapper.map(savedEntities);
    }

}
