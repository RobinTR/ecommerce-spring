package org.robn.ecommerce.brand.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.entity.BrandEntity;
import org.robn.ecommerce.brand.model.mapper.BrandEntityToDomainMapper;
import org.robn.ecommerce.brand.model.mapper.BrandToEntityMapper;
import org.robn.ecommerce.brand.port.BrandReadPort;
import org.robn.ecommerce.brand.port.BrandSavePort;
import org.robn.ecommerce.brand.repository.BrandRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandAdapter implements BrandReadPort, BrandSavePort {

    private final BrandRepository brandRepository;
    private final BrandToEntityMapper brandToEntityMapper;
    private final BrandEntityToDomainMapper brandEntityToDomainMapper;

    @Override
    public List<Brand> findAll() {
        final List<BrandEntity> brandEntities = brandRepository.findAll();

        return brandEntityToDomainMapper.map(brandEntities);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        final Optional<BrandEntity> brandEntity = brandRepository.findById(id);

        return brandEntity.map(brandEntityToDomainMapper::map);
    }

    @Override
    public Brand save(Brand brand) {
        final BrandEntity brandEntity = brandToEntityMapper.map(brand);
        final BrandEntity savedBrandEntity = brandRepository.save(brandEntity);

        return brandEntityToDomainMapper.map(savedBrandEntity);
    }

}
