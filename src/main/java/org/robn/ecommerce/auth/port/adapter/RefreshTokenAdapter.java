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

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenReadPort, RefreshTokenSavePort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenEntityToDomainMapper entityToDomainMapper;
    private final RefreshTokenDomainToEntityMapper domainToEntityMapper;

    @Override
    public Optional<RefreshToken> findByTokenHash(final String tokenHash) {
        final Optional<RefreshTokenEntity> entity = refreshTokenRepository.findByTokenHash(tokenHash);

        return entity.map(entityToDomainMapper::map);
    }

    @Override
    public RefreshToken save(final RefreshToken refreshToken) {
        final RefreshTokenEntity entity = domainToEntityMapper.map(refreshToken);
        final RefreshTokenEntity savedEntity = refreshTokenRepository.save(entity);

        return entityToDomainMapper.map(savedEntity);
    }

}
