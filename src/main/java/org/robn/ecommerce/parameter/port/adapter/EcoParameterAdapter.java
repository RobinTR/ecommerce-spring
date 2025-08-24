package org.robn.ecommerce.parameter.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.parameter.exception.EcoParameterNotFoundException;
import org.robn.ecommerce.parameter.mapper.EcoParameterEntityToDomainMapper;
import org.robn.ecommerce.parameter.model.EcoParameter;
import org.robn.ecommerce.parameter.model.entity.EcoParameterEntity;
import org.robn.ecommerce.parameter.port.EcoParameterReadPort;
import org.robn.ecommerce.parameter.repository.EcoParameterRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EcoParameterAdapter implements EcoParameterReadPort {

    private final EcoParameterRepository ecoParameterRepository;
    private final EcoParameterEntityToDomainMapper entityToDomainMapper;

    @Override
    public List<EcoParameter> findAll() {
        final List<EcoParameterEntity> parameters = ecoParameterRepository.findAll();

        return entityToDomainMapper.map(parameters);
    }

    @Override
    public EcoParameter findByName(final String name) {
        final EcoParameterEntity parameter = ecoParameterRepository.findByName(name).orElseThrow(() -> EcoParameterNotFoundException.of(name));

        return entityToDomainMapper.map(parameter);
    }

}
