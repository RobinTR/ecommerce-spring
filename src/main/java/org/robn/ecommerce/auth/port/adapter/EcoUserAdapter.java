package org.robn.ecommerce.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.EcoUserToEntityMapper;
import org.robn.ecommerce.auth.mapper.UserEntityToEcoUserMapper;
import org.robn.ecommerce.auth.model.EcoUser;
import org.robn.ecommerce.auth.model.entity.EcoUserEntity;
import org.robn.ecommerce.auth.port.EcoUserReadPort;
import org.robn.ecommerce.auth.port.EcoUserSavePort;
import org.robn.ecommerce.auth.repository.EcoUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EcoUserAdapter implements EcoUserReadPort, EcoUserSavePort {

    private final EcoUserRepository ecoUserRepository;
    private final EcoUserToEntityMapper ecoUserToEntityMapper;
    private final UserEntityToEcoUserMapper userEntityToEcoUserMapper;

    @Override
    public EcoUser save(final EcoUser ecoUser) {
        final EcoUserEntity ecoUserEntity = ecoUserToEntityMapper.map(ecoUser);
        final EcoUserEntity savedEntity = ecoUserRepository.save(ecoUserEntity);

        return userEntityToEcoUserMapper.map(savedEntity);
    }

    @Override
    public Optional<EcoUser> findByEmail(final String email) {
        final Optional<EcoUserEntity> user = ecoUserRepository.findByEmail(email);

        return user.map(userEntityToEcoUserMapper::map);
    }

    @Override
    public boolean emailExists(final String email) {
        return ecoUserRepository.existsByEmail(email);
    }

}
