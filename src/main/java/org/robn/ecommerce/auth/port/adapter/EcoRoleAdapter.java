package org.robn.ecommerce.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.mapper.EcoRoleEntityToDomainMapper;
import org.robn.ecommerce.auth.model.EcoRole;
import org.robn.ecommerce.auth.model.entity.EcoRoleEntity;
import org.robn.ecommerce.auth.port.EcoRoleReadPort;
import org.robn.ecommerce.auth.repository.EcoRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EcoRoleAdapter implements EcoRoleReadPort {

    private final EcoRoleRepository ecoRoleRepository;
    private final EcoRoleEntityToDomainMapper entityToDomainMapper;

    @Override
    public Optional<EcoRole> findByName(final String name) {
        final Optional<EcoRoleEntity> entity = ecoRoleRepository.findByName(name);

        return entity.map(entityToDomainMapper::map);
    }

}
