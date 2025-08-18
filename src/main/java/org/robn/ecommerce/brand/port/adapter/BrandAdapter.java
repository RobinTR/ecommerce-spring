package org.robn.ecommerce.brand.port.adapter;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.brand.model.Brand;
import org.robn.ecommerce.brand.model.entity.BrandEntity;
import org.robn.ecommerce.brand.model.mapper.BrandDomainToEntityMapper;
import org.robn.ecommerce.brand.model.mapper.BrandEntityToDomainMapper;
import org.robn.ecommerce.brand.port.BrandReadPort;
import org.robn.ecommerce.brand.port.BrandSavePort;
import org.robn.ecommerce.brand.repository.BrandRepository;
import org.robn.ecommerce.product.port.BrandLookupPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandAdapter implements BrandReadPort, BrandSavePort, BrandLookupPort {

    private final BrandRepository brandRepository;
    private final BrandDomainToEntityMapper brandDomainToEntityMapper;
    private final BrandEntityToDomainMapper brandEntityToDomainMapper;

    @Override
    public List<Brand> findAll() {
        final List<BrandEntity> brandEntities = brandRepository.findAll();

        return brandEntityToDomainMapper.map(brandEntities);
    }

    @Override
    public Optional<Brand> findById(final Long id) {
        final Optional<BrandEntity> brandEntity = brandRepository.findById(id);

        return brandEntity.map(brandEntityToDomainMapper::map);
    }

    @Override
    public Brand save(final Brand brand) {
        BrandEntity brandEntity = brandDomainToEntityMapper.map(brand);
        brandEntity = brandRepository.save(brandEntity);

        return brandEntityToDomainMapper.map(brandEntity);
    }

    @Override
    public Boolean existsById(final Long id) {
        return brandRepository.existsById(id);
    }

}
